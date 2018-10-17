package com.whfp.anti_terrorism.basic;

import org.xutils.common.Callback.ProgressCallback;

/**
 * 文件下载回调
 * @param <ResultType>
 */
public class MyCallback2<ResultType> implements ProgressCallback<ResultType> {

	@Override
	public void onCancelled(CancelledException arg0) {

	}

	@Override
	public void onError(Throwable arg0, boolean arg1) {
		// 可以根据公司的需求进行统一的请求网络失败的逻辑处理
	}

	@Override
	public void onFinished() {

	}

	@Override
	public void onSuccess(ResultType arg0) {
		// 可以根据公司的需求进行统一的请求成功的逻辑处理
	}

	@Override
	public void onLoading(long arg0, long arg1, boolean arg2) {
		// 下载进度
		// progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		// progressDialog.setMessage("亲，努力下载中。。。");
		// progressDialog.show();
		// progressDialog.setMax((int) total);
		// progressDialog.setProgress((int) current);
	}

	@Override
	public void onStarted() {
		// 开始下载
	}

	@Override
	public void onWaiting() {
		// 下载中
	}

}
