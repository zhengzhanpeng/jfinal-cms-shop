package handler;

import com.gz.common.Constant;
import com.gz.utils.FileUtil;
import com.jfinal.handler.Handler;
import com.jfinal.kit.PropKit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;

/**
 * Created by gongzhen on 2018/6/19.
 */
public class ResourceHandler extends Handler{
    @Override
    public void handle(String s, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, boolean[] booleen) {
        HashSet<String> images=new HashSet<>();
        images.add("jpg");
        images.add("jpeg");
        images.add("png");
        images.add("gif");
        String fileExtension= FileUtil.getFileExtension(s);
        if(images.contains(fileExtension)){
            System.out.println(Constant.HOST_PATH+s);
            FileUtil.download(httpServletResponse, PropKit.get("FILE_UPLOAD_PATH")+s);
            booleen[0]=true;
        }
        next.handle(s,httpServletRequest,httpServletResponse,booleen);
    }
}
