package edu.stanford.isis.epad.plugin.lesiontracking.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FileUtils;

import gwtupload.server.UploadAction;
import gwtupload.server.exceptions.UploadActionException;
import gwtupload.shared.UConsts;

public class AnnotationXMLFileUploadServlet extends UploadAction
{
      private static final long serialVersionUID = 1L;

      private Hashtable<String, String> receivedContentTypes = new Hashtable<String, String>();

      /**
       * Maintain a list with received files and their content types.
       */
      private Hashtable<String, File> receivedFiles = new Hashtable<String, File>();

      /**
       * Override executeAction to save the received files in a custom place
       * and delete this items from session.
       */
      @Override
      public String executeAction(HttpServletRequest request, List<FileItem> sessionFiles) throws UploadActionException {
        String response = "";
        for (FileItem item : sessionFiles) {
          if (false == item.isFormField()) {
            try {
              /// Create a new file based on the remote file name in the client
              // String saveName = item.getName().replaceAll("[\\\\/><\\|\\s\"'{}()\\[\\]]+", "_");
              // File file =new File("/tmp/" + saveName);

              /// Create a temporary file placed in /tmp (only works in unix)
              // File file = File.createTempFile("upload-", ".bin", new File("/tmp"));

              /// Create a temporary file placed in the default system temp folder
              String saveName = item.getName().replaceAll("[\\\\/><\\|\\s\"'{}()\\[\\]]+", "_");
              File file = File.createTempFile("upload-", saveName);
              item.write(file);

              /// Save a list with the received files
              receivedFiles.put(item.getFieldName(), file);
              receivedContentTypes.put(item.getFieldName(), item.getContentType());

              if(file != null)
              {
                  File targetFile = new File(getServletContext().getRealPath("/") + "/aim_files/uploads/" + file.getName());
                  FileUtils.copyFile(file, targetFile);
                  /// Send a customized message to the client.
                  response += "File saved as " + targetFile.getAbsolutePath();
              }


            } catch (Exception e) {
              throw new UploadActionException(e);
            }
          }
        }

        /// Remove files from session because we have a copy of them
        removeSessionFileItems(request);

        /// Send your customized message to the client.
        return response;
      }

      /**
       * Get the content of an uploaded file.
       */
      @Override
      public void getUploadedFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fieldName = request.getParameter(UConsts.PARAM_SHOW);
        File f = receivedFiles.get(fieldName);
        if (f != null) {
          response.setContentType(receivedContentTypes.get(fieldName));
          FileInputStream is = new FileInputStream(f);
          copyFromInputStreamToOutputStream(is, response.getOutputStream());
        } else {
          renderXmlResponse(request, response, XML_ERROR_ITEM_NOT_FOUND);
       }
      }

      /**
       * Remove a file when the user sends a delete request.
       */
      @Override
      public void removeItem(HttpServletRequest request, String fieldName)  throws UploadActionException {
        File file = receivedFiles.get(fieldName);
        receivedFiles.remove(fieldName);
        receivedContentTypes.remove(fieldName);
        if (file != null) {
          file.delete();
        }
      }
}
