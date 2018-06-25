package com.gz.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.druid.util.StringUtils;

public class StringUtil {
	public static String getIdByUrl(String url) {
		System.out.println(url);
		int index= url.indexOf("id")+3 ;
		int end=url.indexOf("&rn");
		System.out.println(end);
		if(end>index)
			return url.substring(index,end);
		System.out.println(index);
		System.out.println(url.substring(index));
		return url.substring(index);
	}

	public static boolean isEmpty(String str) {
		if (str==null||str.trim().equals("")) {
			return true;
		}
		return false;
	}
	
	public static boolean isNum(String str){
		if(isEmpty(str)){
			return false;
		}
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
	}

	public static boolean notBlank(String inputRandomCode) {
		// TODO Auto-generated method stub
		return false;
	}
	public static String join(String[] str) {
		StringBuffer sb = new StringBuffer();
		 for(int i = 0; i < str.length; i++)
		 { 
		 sb. append(str[i]);
		 }
		 String s = sb.toString();
		return s;
	}

	public static boolean exitstr(String key, String[] quarter) {
		if(isEmpty(key)||quarter==null||quarter.length<=0){
			return false;
		}
		for (String q : quarter) {
			if(key.equals(q))
				return true;
		}
		return false;
	}
	public static boolean exitstr(String key, String quarter) {
		if(quarter!=null&&key!=null&&quarter.indexOf(key)>=0){
			return true;
		}
		return false;
	}
	
	public static int matchSite(String string) {
		String regex=".*?detail\\.tmall\\.com/item.*?";
		return string.matches(regex)?2:1;
	}
	
	public static String UTF8Wrapper(String src) {
		String s = null;
		try {
			s = new String(src.getBytes(), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			s = src;
		}
		return s;
	}

	public static String get2BitStr(int num){
		if(num<10){
			return "0"+num;
		}else{
			return ""+num;
		}
	}
	
	/**
	 * 将字符串数组转换为splitStr分割的字符串
	 */
	public static String arrayToString(ArrayList<String> arr, String splitStr) {
		if (arr == null || arr.size() == 0)
			return "";
		StringBuilder sb = new StringBuilder();
		for (String string : arr) {
			sb.append(splitStr);
			sb.append(string);
		}
		return sb.substring(splitStr.length());
	}

	/**
	 * 字符串分割成int数组
	 * @param str
	 * @param flag
	 * @return
	 */
	public static  Integer[] strToArrayInt(String str,String flag){
		if(StringUtil.isEmpty(str)||StringUtil.isEmpty(flag)){
			return null;
		}
		Integer[] result;
		if (str.contains(flag)) {
			String[] r= str.split(flag);
			result=new Integer[r.length];
			int i=0;
			for(String s:r){
				result[i++]=Integer.parseInt(s);
			}
			return result;
		} else {
			result = new Integer[]{Integer.parseInt(str)};
		}
		return result;
	}

	/**
	 * 获取一个url的base url（除参数之外）
	 */
	public static String getBaseUrl(String url) {
		int index = url.indexOf('?');
		return url.substring(0, index);
	}
	
	/**
	 * 获取一个url的参数,参数以？开始
	 */
	public static Map<String, String> getParams(String url) {
		int index = url.indexOf('?');
		String params = url.substring(index + 1);
		return decodeParams(params);
	}
	/**
	 * 获取一个url的参数,参数以#开始
	 */
	public static Map<String, String> parseUrl(String url) {
		int index = url.indexOf('#');
		String params = url.substring(index + 1);
		return decodeParams(params);
	}
	/**
	 * 解析url参数 如：type=3&sellerId=78&isShare=1&userId=375&id=&shareTime=1490944247699
	 */
	public static Map<String, String> decodeParams(String p) {
		Map<String, String> params = new HashMap<>();
		if (p != null) {
			String[] array = p.split("&");
			for (String parameter : array) {
				String[] v = parameter.split("=");
				String value = v.length < 2 ? "" : v[1];
				params.put(URLDecoder.decode(v[0]),
						URLDecoder.decode(value));
				System.out.println("key="+URLDecoder.decode(v[0])+",value="+URLDecoder.decode(value));
			}
		}
		return params;
	}
	public static String replaceBlank(String str) {
		String dest = "";
		if (str!=null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}
	/**
	 * 判断传进来的字符串，是否
	 * 大于指定的字节，如果大于递归调用
	 * 直到小于指定字节数 ，一定要指定字符编码，因为各个系统字符编码都不一样，字节数也不一样
	 * @param s
	 *            原始字符串
	 * @param num
	 *            传进来指定字节数
	 * @return String 截取后的字符串

	 * @throws UnsupportedEncodingException
	 */
	public static String substring(String s,int num)throws Exception{
		int changdu = s.getBytes("UTF-8").length;
		if(changdu > num){
			s = s.substring(0, s.length() - 1);
			s = substring(s,num);
		}
		return s;
	}
	
	/**
	 * 过滤特殊字符
	 * @param str
	 * @return
	 */
	public static String filter(String str) {  
        if(str==null||str.trim().isEmpty()){  
            return str;  
        }  
        String pattern="[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]";  
        String reStr="*";  
        Pattern emoji=Pattern.compile(pattern,Pattern.UNICODE_CASE | Pattern . CASE_INSENSITIVE);  
        Matcher  emojiMatcher=emoji.matcher(str);
        str=emojiMatcher.replaceAll(reStr);  
        return str;  
    }
	
	 /** 
     * 过滤emoji 或者 其他非文字类型的字符 
     *  
     * @param source 
     * @return 
     */  
    public static String filterEmoji(String source) {  
  
        if (!containsEmoji(source)) {  
            return source;// 如果不包含，直接返回  
        }  
        // 到这里铁定包含  
        StringBuilder buf = null;  
  
        int len = source.length();  
  
        for (int i = 0; i < len; i++) {  
            char codePoint = source.charAt(i);  
  
            if (isEmojiCharacter(codePoint)) {  
                if (buf == null) {  
                    buf = new StringBuilder(source.length());  
                }  
  
                buf.append(codePoint);  
            } else {  
            }  
        }  
  
        if (buf == null) {  
            return source;// 如果没有找到 emoji表情，则返回源字符串  
        } else {  
            if (buf.length() == len) {// 这里的意义在于尽可能少的toString，因为会重新生成字符串  
                buf = null;  
                return source;  
            } else {  
                return buf.toString();  
            }  
        }  
  
    }  
	

    /** 
     * 检测是否有emoji字符 
     *  
     * @param source 
     * @return 一旦含有就抛出 
     */  
    public static boolean containsEmoji(String source) {  
        if (StringUtils.isEmpty(source)) {
            return false;  
        }  
  
        int len = source.length();  
  
        for (int i = 0; i < len; i++) {  
            char codePoint = source.charAt(i);  
            if (isEmojiCharacter(codePoint)) {  
                // do nothing，判断到了这里表明，确认有表情字符  
                return true;  
            }  
        }  
  
        return false;  
    }  
  
    private static boolean isEmojiCharacter(char codePoint) {  
        return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA)  
                || (codePoint == 0xD)  
                || ((codePoint >= 0x20) && (codePoint <= 0xD7FF))  
                || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))  
                || ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));  
    }  
  
   
    
	/**
	 * 编码是否有效
	 * @param text
	 * @return
	 */
	private static final boolean Utf8codeCheck(String text){
		String sign = "";
		if (text.startsWith("%e"))
			for (int i = 0, p = 0; p != -1; i++) {
				p = text.indexOf("%", p);
				if (p != -1)
					p++;
				sign += p;
			}
		return sign.equals("147-1");
	}
	/**
	 * Utf8URL解码
	 * @param text
	 * @return
	 */
	public static final String Utf8URLdecode(String text) {
		String result = "";
		int p = 0;
		if (text!=null && text.length()>0){
			text = text.toLowerCase();
			p = text.indexOf("%e");
			if (p == -1) return text;
			while (p != -1) {
				result += text.substring(0, p);
				text = text.substring(p, text.length());
				if (text == "" || text.length() < 9) return result;

				result += CodeToWord(text.substring(0, 9));
				text = text.substring(9, text.length());
				p = text.indexOf("%e");
			}
		}
		return result + text;
	}
	/**
	 * utf8URL编码转字符
	 * @param text
	 * @return
	 */
	private static final String CodeToWord(String text) {
		String result;
		if (Utf8codeCheck(text)) {
			byte[] code = new byte[3];
			code[0] = (byte) (Integer.parseInt(text.substring(1, 3), 16) - 256);
			code[1] = (byte) (Integer.parseInt(text.substring(4, 6), 16) - 256);
			code[2] = (byte) (Integer.parseInt(text.substring(7, 9), 16) - 256);
			try {
				result = new String(code, "UTF-8");
			}catch (UnsupportedEncodingException ex) {
				result = null;
			}
		}
		else {
			result = text;
		}
		return result;
	}
	/**
	 * 判断是否Utf8Url编码
	 * @param text
	 * @return
	 */
	public static final boolean isUtf8Url(String text) {
		text = text.toLowerCase();
		int p = text.indexOf("%");
		if (p != -1 && text.length() - p > 9) {
			text = text.substring(p, p + 9);
		}
		return Utf8codeCheck(text);
	}

	/**
	 * 大陆号码或香港号码均可
	 */
	public static boolean isPhoneLegal(String str) {
		return isChinaPhoneLegal(str) || isHKPhoneLegal(str);
	}

	/**
	 * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数
	 * 此方法中前三位格式有：
	 * 13+任意数
	 * 15+除4的任意数
	 * 18+除1和4的任意数
	 * 17+除9的任意数
	 * 147
	 */
	public static boolean isChinaPhoneLegal(String str)  {
		String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(str);
		return m.matches();
	}

	/**
	 * 香港手机号码8位数，5|6|8|9开头+7位任意数
	 */
	public static boolean isHKPhoneLegal(String str) {
		String regExp = "^(5|6|8|9)\\d{7}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(str);
		return m.matches();
	}
}
