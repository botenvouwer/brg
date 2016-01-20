package restService.businessRuleGenerator;

import businessRuleGenerator.dao.DAO;
import businessRuleGenerator.dao.DAOException;
import businessRuleGenerator.dao.DAOFactory;
import businessRuleGenerator.domain.database.ConnectionDetails;
import businessRuleGenerator.domain.database.Query;
import restService.response.Result;

import javax.ws.rs.*;
import java.sql.SQLException;

/**
 * Created by william on 19-Jan-16.
 */

@Path("/database")
public class DatabaseCommunicationService{

    @POST
    @Path("/tables")
    @Consumes("application/json")
    @Produces("application/json")
    public Result getTables(ConnectionDetails dbdetails) throws SQLException {

        Result result = new Result();

        DAO dao = null;
        try {
            dao = DAOFactory.build(dbdetails);
            result.setResult(dao.getTables());
        } catch (DAOException e) {
            result.setError("DAOException: " + e.getMessage());
        }
        return result;
    }

    @POST
    @Path("/columns/{tableName}")
    @Consumes("application/json")
    @Produces("application/json")
    public Result getColumnNames(ConnectionDetails dbdetails, @PathParam("tableName")String tableName) throws SQLException {

        Result result = new Result();

        DAO dao = null;
        try {
            dao = DAOFactory.build(dbdetails);
            result.setResult(dao.getColumns(tableName));
        } catch (DAOException e) {
            result.setError("DAOException: " + e.getMessage());
        }
        return result;
    }

    @POST
    @Path("/query/")
    @Consumes("application/json")
    @Produces("application/json")
    public Result doQuery(Query q) {

        Result result = new Result();

        DAO dao = null;
        try {
            dao = DAOFactory.build(q.con);
            String errorString = dao.doQuery(q.query);
            if(errorString != "") result.setError("Query returned warning(s): " + errorString);

        } catch(DAOException daoe) {
            result.setError("DAOException: " + daoe.getMessage());
        }
        return result;
    }
}
