package cs;

import com.safety.Aes;

import java.io.*;
import java.net.Socket;

public class ServerUploadThread implements Runnable{

    private Socket s;
    
    public ServerUploadThread(Socket s) {
        this.s=s;
    }

    @Override
    public void run() {
        //接受数据写入到文件
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));

            //解决名称冲突问题
            /*
            int count = 1;
            File file = new File("C:\\Users\\79361\\Desktop\\images\\Copy["+count+"].txt");
            while (file.exists()){
                count++;
                file = new File("C:\\Users\\79361\\Desktop\\images\\Copy["+count+"].txt");
            }*/
            //BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            String line;
            while ((line=br.readLine())!=null){
                bw.write(line);
                bw.newLine();
                bw.flush();
            }

            //给出反馈
            BufferedWriter bwServer = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
            bwServer.write("文件上传成功");
            bwServer.newLine();
            bwServer.flush();

        }catch (IOException e){
            e.printStackTrace();
        }

    }
}