package common;
/**
 * Request class allows to keep useful information whose be treated by the server
 * - operation_type -> to know if the client wants a select/insert/delete/update or more specific request
 * - target -> to know which DAO is concerned
 * - source -> to know who is connected
 * - obj -> for insert operation for example, we need to put an object with the request, it is convert in json before
 * It concerned two sides (client request and server treatment about it) --> common
 */
public class Request {
	private String operation_type;
	private String target;
	private String source;
	private String obj;
	
	public Request () {
		operation_type = "";
		target = "";
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

	public void setSource(String source) {
		this.source = source;
	}

	public String getObj() {
		return obj;
	}

	public void setObj(String obj) {
		this.obj = obj;
	}
	
	public String toString() {
		return "operation_type : " + operation_type  + " ; target = " + target + " ; source : " + source + " ; object : " + obj; 
	}
	
}
