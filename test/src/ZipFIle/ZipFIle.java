package ZipFIle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFIle {
    public static void main(String[] args) throws IOException {
        File source = new File("D:\\JavaLearning\\JavaLearning\\test\\src\\ZipFIle");
        File destination = new File(source.getParentFile(), source.getName() + ".zip");
        zipFiles(source, destination);


    }
    public static void zipFiles(File source, File destination) throws IOException {
        //获取压缩包的文件名
        String[] fileString = source.getName().split("\\\\");
        String zipFileName;
        if (fileString.length >= 2){
            zipFileName = fileString[fileString.length-1];
        }else {
            System.out.println("压缩源路径错误");
            return;
        }

        //创建压缩流
        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(new File(destination, zipFileName)));
        File[] files = source.listFiles();
        //遍历获取文件，分别迭代压缩文件夹和文件，
        for (File file : files) {
            //文件：变成ZipEntry对象，放入压缩包中
            if (file.isDirectory()){
                ZipEntry zipEntry = new ZipEntry(file.getName());
                zipOutputStream.putNextEntry(zipEntry);
               // zipFiles(new File(source, file.getName()), )
            }

        }
        //zipOutputStream.putNextEntry(zipEntry)



    }
}
