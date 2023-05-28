package com.parker.unicornListener.service;

import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class FTPService {

	public boolean login(String user, String password, FTPClient ftpClient) throws IOException {
		return ftpClient.login(user, password);
	}

	public FTPClient connect(String hostName, int portNum) {
		FTPClient ftpClient = new FTPClient();
		try {
			ftpClient.connect(hostName, portNum);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ftpClient;
	}

	public FTPFile[] getCurrentDirFileList(FTPClient ftpClient) {
		try {
			return ftpClient.listFiles();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void disconnect(FTPClient ftpClient) {
		if (ftpClient.isConnected())
			try {
				ftpClient.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
}
