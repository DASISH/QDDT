package no.nsd.qddt.logic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.http.HttpServletResponse;

public class FileStreamer {
   
   private String filename;
   private HttpServletResponse response;
   private int fileLength;
   private InputStream ins;

   
   public void setFilename(String filename) {
      this.filename = filename;
   }

   public void setResponse(HttpServletResponse response) {
      this.response = response;
   }

   public void setFileLength(int fileLength) {
      this.fileLength = fileLength;
   }

   public void setIns(InputStream ins) {
      this.ins = ins;
   }
   
   
   public void streamFile() throws IOException {
      BufferedInputStream bis = null;
      BufferedOutputStream bos = null;
      
      try {
         response.setContentType(this.getMimetype());
         response.setHeader("Content-disposition", "attachment; filename=" + filename);
         response.setContentLength(fileLength);

         bis = new BufferedInputStream(ins);
         bos = new BufferedOutputStream(response.getOutputStream());

         byte[] buff = new byte[Math.min(3000000, fileLength)];

         while (true) {
            int bytesRead = bis.read(buff, 0, buff.length);
            if (bytesRead == -1) {
               break;
            }
            bos.write(buff, 0, bytesRead);
            bos.flush();
         }
         
      } finally {
         try {
            if (bis != null) {
               bis.close();
            }
         } catch (IOException ignored) { }
         try {
            if (bos != null) {
               bos.close();
            }
         } catch (IOException ignored) { }
      }
      
   }

   
   
   private String getMimetype() {
      String mimetype = "application/octet-stream";
      
      if (filename.endsWith(".zip")) {
         mimetype = "application/zip";
      } else if (filename.endsWith(".pdf")) {
         mimetype = "application/pdf";
      } else if (filename.endsWith(".doc")) {
         mimetype = "application/msword";
      } else if (filename.endsWith(".rtf")) {
         mimetype = "text/rtf";
      } else if (filename.endsWith(".spss")) {
         mimetype = "application/x-spss";
      } else if (filename.endsWith(".spo")) {
         mimetype = "application/x-spo";
      } else if (filename.endsWith(".spot")) {
         mimetype = "text/vnd.in3d.spot";
      } else if (filename.endsWith(".sas")) {
         mimetype = "application/x-sas";
      } else if (filename.endsWith(".xls")) {
         mimetype = "application/vnd.ms-excel";
      } else if (filename.endsWith(".xlsx")) {
         mimetype = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
      } else if (filename.endsWith(".docx")) {
         mimetype = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
      }
    
      return mimetype;
   }   
   
   
}
