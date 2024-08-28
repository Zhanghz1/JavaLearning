package ZipFIle;

import java.io.*;
import java.util.zip.*;

class ZipFile {
    public static void main(String[] args) throws IOException {
        File source = new File("D:\\JavaLearning\\JavaLearning\\test\\src\\ZipFile");
        File destination = new File(source.getParentFile(), source.getName() + ".zip");
        zipFiles(source, destination);
    }

    public static void zipFiles(File source, File destination) throws IOException {
        // 创建压缩流
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(destination))) {
            compressDirectoryToZipfile(source.getParentFile().getPath(), source, zipOutputStream);
        }
    }

    private static void compressDirectoryToZipfile(String rootDir, File source, ZipOutputStream out) throws IOException {
        String entryName = source.getPath().substring(rootDir.length() + 1);
        if (source.isDirectory()) {
            if (!entryName.isEmpty()) {
                out.putNextEntry(new ZipEntry(entryName + "/"));
                out.closeEntry();
            }
            for (File file : source.listFiles()) {
                compressDirectoryToZipfile(rootDir, file, out);
            }
        } else {
            try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(source))) {
                out.putNextEntry(new ZipEntry(entryName));
                byte[] buffer = new byte[1024];
                int count;
                while ((count = in.read(buffer)) != -1) {
                    out.write(buffer, 0, count);
                }
                out.closeEntry();
            }
        }
    }
}
