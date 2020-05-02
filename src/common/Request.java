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
	private String source;
	private Object obj;
	
	public Request () {
		operation_type = "";
		target = "";
		source = "";
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

	public String getSource() {
		return source;
	}

	public void setSource(String client) {
		this.source = client;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
	
}
