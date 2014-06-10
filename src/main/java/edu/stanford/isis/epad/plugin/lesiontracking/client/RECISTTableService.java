package edu.stanford.isis.epad.plugin.lesiontracking.client;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

@Path("/services/recist/table")
public interface RECISTTableService extends RestService
{
    @GET
    public void getRECISTChart(MethodCallback<String> callback);
}