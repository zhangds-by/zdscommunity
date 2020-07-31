package com.quark.common.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberUtils extends org.apache.commons.lang3.math.NumberUtils{
	
	public final static DecimalFormat FORMAT = new DecimalFormat("0.##");
	
	public final static DecimalFormat FORMAT_1 = new DecimalFormat("0.#");
	public final static DecimalFormat FORMAT_00 = new DecimalFormat("0.00");
	public final static DecimalFormat FORMAT_0 = new DecimalFormat("0.0");
	
	private static Log log = LogFactory.getLog(NumberUtils.class);
	
	public static int primitive(Integer integer){
		return integer == null?0:integer.intValue();
	}

	public static int primitive(Integer integer, int defaultNum){
		return integer == null?defaultNum:integer.intValue();
	}
	
	public static long primitive(Long long1){
		return long1 == null?0:long1.longValue();
	}
	
	public static double primitive(Double double1){
		return double1 == null?0:double1.doubleValue();
	}
	
	public static float primitive(Float float1){
		return float1 == null?0:float1.floatValue();
	}
	
	/**
	 * 字符串解析成数字， 不报错
	 * 解析失败为空
	 */
	public static Integer parseInteger(String str, boolean isLog){
		if(CommonUtils.empty(str))
			return null;
		try{
			return Integer.parseInt(str);
		}catch(Exception e){
			if(isLog){
//				log.error(String.format("[%s]字符串解析成Integer失败", str));
			}
		}
		return null;
	}
	
	/**
     * 字符串解析成数字， 不报错
     * 解析失败为空
     */
    public static Integer parseInteger(String str){
    	return parseInteger(str, false);
    }

	/**
	 * 字符串解析成数字， 不报错
	 * 解析失败为空
	 */
	public static Float parseFloat(String str){
		if(CommonUtils.empty(str))
			return null;
		try{
			return Float.parseFloat(str);
		}catch(Exception e){
//    		log.error(String.format("[%s]字符串解析成Double失败", str));
		}
		return null;
	}

    /**
     * 字符串解析成数字， 不报错
     * 解析失败为空
     */
    public static Double parseDouble(String str){
    	if(CommonUtils.empty(str))
    		return null;
    	try{
    		return Double.parseDouble(str);
    	}catch(Exception e){
//    		log.error(String.format("[%s]字符串解析成Double失败", str));
    	}
    	return null;
    }
    /**
     * 字符串解析成数字，解析失败警告
     */
    public static Double parseDouble(String str, String message){
    	if(CommonUtils.empty(str))
    		return null;
    	try{
    		return Double.parseDouble(str);
    	}catch(Exception e){
//    		log.warn(String.format("%s：内容[%s]解析成Double失败", message, str));
    	}
    	return null;
    }
    
    /**
     * 字符串解析成数字， 不报错
     * 解析失败为空
     */
    public static Long parseLong(String str){
    	if(CommonUtils.empty(str))
    		return null;
    	try{
    		return Long.parseLong(str);
    	}catch(Exception e){
//    		log.error(String.format("[%s]字符串解析成Long失败", str));
    	}
    	return null;
    }

	public static Long parseLong(Object obj){
    	if(obj==null){return null;}
		try{
			return  Long.parseLong(obj.toString());
		}catch(Exception e){
//			log.error(String.format("[%s]解析成Long失败", obj.toString()));
			return null;
		}
	}
	
    /**
     * 字符串解析成数字， 不报错
     * 解析失败为空
     */
    public static Boolean parseBoolean(String str){
    	if(CommonUtils.empty(str))
    		return null;
    	try{
    		return Boolean.parseBoolean(str);
    	}catch(Exception e){
//    		log.error(String.format("[%s]字符串解析成Boolean失败", str));
    	}
    	return null;
    }

	public static Double add(Double sum, Double fluidSize) {
		if(sum == null){
			return fluidSize;
		}else if(fluidSize == null){
			return sum;
		}
		return sum + fluidSize;
	}
	
	public static Integer add(Integer sum, Integer fluidSize) {
		if(sum == null){
			return fluidSize;
		}else if(fluidSize == null){
			return sum;
		}
		return sum + fluidSize;
	}

    public static int toInt(String value, int defaultValue){
    	
    	Integer integer = NumberUtils.parseInteger(value);
    	
    	return integer == null? defaultValue:integer;
    }
    
    /**
     * 使用默认金额格式化
     */
    public static String format(BigDecimal bigDecimal){
    	String string = FORMAT.format(bigDecimal);
    	return string;
    }
    
    /**
     * 使用默认金额格式化
     */
    public static String format(Double d){
    	String string = FORMAT.format(d);
    	return string;
    }

	public static boolean primitive(Boolean bool) {
		boolean b = Boolean.TRUE.equals(bool);
		return b;
	}

	public static String removeZeroAndDot(Double figure){
		String s=String.valueOf(figure);
		if(s.indexOf(".") > 0){
			s = s.replaceAll("0+?$", "");//去掉多余的0
			s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
		}
		return s;

	}
	
	public static double formatAndParse(Double d, DecimalFormat format){
		double temp = primitive(d);
		String tempStr = format.format(temp);
		d = NumberUtils.parseDouble(tempStr);
		return primitive(d);
	}
	
	public static <T extends Serializable> double sum(Double... values ){
		if(values == null){
			return 0d;
		}
		Double sum = 0d;
		for(Double value : values){
			sum += primitive(value);
		}
		return sum;
	}
	
    
    public static <T extends Comparable<T>> T getMax(Set<T> set){
    	if(CommonUtils.empty(set)){
    		return null;
    	}
    	T max = null;
    	for(T number : set){
    		if(max == null){
    			max = number;
    		}else{
    			if(number == null){
    				continue;
    			}
    			if(max.compareTo(number) < 0){
    				max = number;
    			}
    		}
    	}
    	return max;
    }

	public static <T, R extends Comparable<R>> R getMax(Collection<T> set, Function<T, R> function){
		if(CommonUtils.empty(set)){
			return null;
		}
		R max = null;
		for(T record : set){
			R number = function.apply(record);
			if(max == null){
				max = number;
			}else{
				if(number == null){
					continue;
				}
				if(max.compareTo(number) < 0){
					max = number;
				}
			}
		}
		return max;
	}
    
    public static <T extends Comparable<T>> T getMin(Set<T> set){
    	if(CommonUtils.empty(set)){
    		return null;
    	}
    	T min = null;
    	for(T number : set){
    		if(min == null){
    			min = number;
    		}else{
    			if(number == null){
    				continue;
    			}
    			if(min.compareTo(number) > 0){
    				min = number;
    			}
    		}
    	}
    	return min;
    }

    public static <T> T maxIgnoreNull(List<T> list, Comparator<T> compare){
    	if(CommonUtils.size(list) == 0){
    		return null;
    	}else if(CommonUtils.size(list) == 1){
    		return list.get(0);
    	}
    	T min = null;
    	for(int i=0; i< CommonUtils.size(list); i++){
    		T temp = list.get(i);
    		if(min == null){
    			min = temp;
    		}else if(temp != null && compare.compare(min, temp) < 0){
    			min = temp;
    		}
    	}
    	return min;
    }

    public static boolean equals(Integer number1,Integer number2){
    	if(number1==null||number2==null){
    		return  false;
		}
		return (number1.equals(number2));
	}

	public static boolean equals(Long number1,Long number2){
		if(number1==null||number2==null){
			return  false;
		}
		return (number1.equals(number2));
	}

	/**
	 * 获取文本一个数字
	 * 输入 --> 输出
	 * 23.8sh123.56 -->23.8
	 * 23.8 --> 23.8
	 * 23 --> 23.0
	 * 23.8sh -> 23.8
	 * s23.8g -> 23.8
	 * sh --> null
	 **/
	public static Double getFirstNumber(String value) {
		if(CommonUtils.empty(value)) {
			return null;
		}
		String regex="[0-9]+(\\.[0-9]+)?";
		Double sum = null;
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(value);
		if(m.find()){
			sum = add(sum, Double.parseDouble(m.group()));
		}
		return sum;
	}

}
