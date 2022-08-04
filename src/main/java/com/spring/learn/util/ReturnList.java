package com.spring.learn.util;

/**
 * 返回结果统一封装
 */
public class ReturnList<T> {

	private static final int SUCCESS = 0;
	private static final int FAIL = 1;

	private boolean success;
	private String msg;
	private int code;
	private T data;

	private ReturnList(boolean success, String msg, int code, T data) {
		this.success = success;
		this.msg = msg;
		this.code = code;
		this.data = data;
	}

	public static <T> ReturnList<T> success(T data) {
		return success("操作成功", data);
	}

	public static <T> ReturnList<T> success() {
		return success(null);
	}

	public static <T> ReturnList<T> success(String msg, T data) {
		return ReturnList.<T>builder()
				.success(true)
				.msg(msg)
				.code(SUCCESS)
				.data(data)
				.build();
	}

	public static <T> ReturnList<T> error(int code, String msg) {
		return ReturnList.<T>builder()
				.success(false)
				.msg(msg)
				.code(code)
				.build();
	}

	public static <T> ReturnList<T> error(String msg) {
		return error(FAIL, msg);
	}

	public static <T> ReturnList<T> error() {
		return error("操作失败");
	}

	public static <T> ResultListBuilder<T> builder() {
		return new ResultListBuilder<>();
	}

	public boolean isSuccess() {
		return this.success;
	}

	public String getMsg() {
		return this.msg;
	}

	public int getCode() {
		return this.code;
	}

	public Object getData() {
		return this.data;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ReturnList{" +
				"success=" + success +
				", msg='" + msg + '\'' +
				", code=" + code +
				", data=" + data +
				'}';
	}

	public static class ResultListBuilder<T> {
		private boolean success;
		private String msg;
		private int code;
		private T data;

		ResultListBuilder() {
		}

		public ResultListBuilder<T> success(boolean success) {
			this.success = success;
			return this;
		}

		public ResultListBuilder<T> msg(String msg) {
			this.msg = msg;
			return this;
		}

		public ResultListBuilder<T> code(int code) {
			this.code = code;
			return this;
		}

		public ResultListBuilder<T> data(T data) {
			this.data = data;
			return this;
		}

		public ReturnList<T> build() {
			return new ReturnList<>(success, msg, code, data);
		}

		@Override
		public String toString() {
			return "ResultListBuilder{" +
					"success=" + success +
					", msg='" + msg + '\'' +
					", code=" + code +
					", data=" + data +
					'}';
		}
	}
}
