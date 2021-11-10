package com.trans;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class clientDownloadFile implements Runnable {
    private Socket socket;
    //private int portReal;
    private String fileName;

    public clientDownloadFile(Socket socket, String fileName) {
        this.socket = socket;
        //this.portReal = portReal;
        this.fileName = fileName;
    }


    @Override
    public void run() {
        //io����װ�ļ���
        try {
            InputStream is = new ByteArrayInputStream(this.fileName.getBytes(StandardCharsets.UTF_8));
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            //�����
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                bw.write(line);
                bw.newLine();
                bw.flush();
            }
            //��ֹ�����������������ܸ���������
            socket.shutdownOutput();

            //���ܼ��ܵ��ļ���Ϣ
            //�ͻ��˽��շ���
            BufferedReader brClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String data = brClient.readLine();
            System.out.println("�����������ģ�"+data);

            //�ͷ���Դ
            socket.close();

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}