package subhabrata.service_caller;

public interface IResult {

	class ServerResponse<T> {
		private T response;
		public void addResponse(T response){
			this.response = response;
		}

		public T getResponse() {
			return response;
		}

	}

	void onResult(ServerResponse<String>  serverResponse);
	void onError(Throwable throwable);
}
