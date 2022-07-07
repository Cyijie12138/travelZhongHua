package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileController {
	
	private final static  String PATH  ; //uploadFile 文件存放的路径
	static {
		File file = new File("uploadFile");
		if(!file.exists()){//空   file.exists()存在是true
            file.mkdirs();
        }
		PATH = file.getAbsolutePath()+File.separator;
	}
	@CrossOrigin
	@RequestMapping("/file/uploadImg")
	@ResponseBody
	public String uploadImg(@RequestPart("img") MultipartFile img,HttpServletRequest request)throws IOException{
		System.out.println("调用到图片上传接口");
		System.out.println("上传的文件的大小为："+img.getSize());
		//上传的文件名称
        String originalFilename = img.getOriginalFilename(); //原来的文件名称  aaa.jpg
        String prefix = originalFilename.substring(0, originalFilename.lastIndexOf('.')); //文件名称
        String suffix = originalFilename.substring(originalFilename.lastIndexOf('.')); //.jpg

        String fileName= prefix +"_"+ System.currentTimeMillis() +suffix; //前缀_时间戳.jpg
        
        img.transferTo(new File(PATH + File.separator + fileName));
		return fileName;
	}
	
	@CrossOrigin
	@RequestMapping(path = "/file/getImg/{fileName:.+}", method=RequestMethod.GET)
	public void getImg(@PathVariable String fileName, HttpServletResponse response)throws IOException {
		System.out.println("调用到获取图片接口，文件名称为"+fileName);
		OutputStream os = null;
		InputStream is = null;
		try {
            String contentType="";
            String ext="";
            if(fileName.endsWith("jpg")||fileName.endsWith("jpeg")) {
                contentType="image/jpeg";
                ext="jpg";
            }else if(fileName.endsWith("png")) {
                contentType="image/png";
                ext="png";
            }
            is = new FileInputStream(new File(PATH+fileName));

            BufferedImage image = ImageIO.read(is);
            response.setContentType(contentType);
            os = response.getOutputStream();
            if (image != null) {
                ImageIO.write(image,ext, os);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                is.close();
            }
            if (os != null) {
                os.flush();
                os.close();
            }
        }
	}
	
}
