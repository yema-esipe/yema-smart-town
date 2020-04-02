package common;
/**
 * Request class allows to keep useful information whose be treated by the server
 * - operation_type -> to know if the client wants a select/insert/delete or update request
 * - target -> to know which DAO is concerned
 * - request -> in SQL, based on client request
 * - client -> to know who is connected
 * It concerned two sides (client request and server treatment about it) --> common
 */
public class Request {
	private String operation_type;
	private String target;
	private String request;
	private String client;
	
	public Request () {
		operation_type = "";
		target = "";
		request = "";
		client = "";
	}

	public String getOperation_type() {
		return operation_type;
	}

	public void setOperation_type(String operation_type) {
		this.operation_type = operation_type;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}
	
}
