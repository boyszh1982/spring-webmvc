package com.boyz.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	public static void main(String[] args) {

		String t = "aaaaaaaaaa<table></table>aaa<table></table>";
		String s = "<TABLE";

		// t="";

		System.out.println("length=" + t.length());

		System.out.println(t.indexOf(s, 0));
		System.out.println(ignoreCaseIndexOf(t, s, 0));

		System.out.println(t.lastIndexOf(s));
		System.out.println(ignoreCaseLastIndexOf(t, s));
	}
	
	/**
	 * if(regularContain("abdefegerere", new String[]{"ab","(?i)cd*"} )  )
	 * @param msg
	 * @param validate
	 * @return
	 */
	public static boolean regularContain(String msg   , String[] validate ){
		boolean flag = false ;
		for(int i=0;i < validate.length;i++){
			String validateStr = validate[i];
			Pattern p = Pattern.compile(validateStr);
			Matcher m = p.matcher(msg);
			flag = m.matches();
			if(flag)
				break;
		}
        return flag;
    }
	
	/**
	 * ��ȡmsg����regularƥ����ַ�������
	 * ���Դ�Сд����д��
	 * regularSubstring("Vgop","(?i)vgop")
	 * @param msg
	 * @param regular
	 * @return
	 */
	public static List<String> regularSubstring(String msg   , String regular ){
			List<String> list = new ArrayList<String>();
			Pattern p = Pattern.compile(regular);
			Matcher m = p.matcher(msg);
			while(m.find()){
				list.add(m.group());
			}
			return list;
	}
	
	/**
	 * ����ָ�����ַ����ڴ��ַ����е�һ�γ��ִ�����������ָ����������ʼ�������ִ�С��
	 * 
	 * @param subject
	 *            �������ַ�����
	 * @param search
	 *            Ҫ���ҵ����ַ�����
	 * @return ָ�����ַ����ڴ��ַ����е�һ�γ��ִ�����������ָ����������ʼ��
	 */
	public static int ignoreCaseIndexOf(String subject, String search) {
		return ignoreCaseIndexOf(subject, search, -1);
	}

	/**
	 * ����ָ�����ַ����ڴ��ַ����е�һ�γ��ִ�����������ָ����������ʼ�������ִ�С��
	 * 
	 * @param subject
	 *            �������ַ�����
	 * @param search
	 *            Ҫ���ҵ����ַ�����
	 * @param fromIndex
	 *            ��ʼ���ҵ�����λ�á���ֵû�����ƣ������Ϊ����������Ϊ 0 ��Ч��ͬ���������������ַ�����
	 *            ��������ڴ��ַ����ĳ��ȣ����������ڴ��ַ������ȵ�Ч����ͬ������ -1��
	 * @return ָ�����ַ����ڴ��ַ����е�һ�γ��ִ�����������ָ����������ʼ��
	 */
	public static int ignoreCaseIndexOf(String subject, String search,
			int fromIndex) {

		// ���������ַ�����������ַ���Ϊ��ʱ���׳���ָ���쳣��
		if (subject == null || search == null) {
			throw new NullPointerException("����Ĳ���Ϊ��");
		}

		fromIndex = fromIndex < 0 ? 0 : fromIndex;

		if (search.equals("")) {
			return fromIndex >= subject.length() ? subject.length() : fromIndex;
		}

		int index1 = fromIndex;
		int index2 = 0;

		char c1;
		char c2;

		loop1: while (true) {

			if (index1 < subject.length()) {
				c1 = subject.charAt(index1);
				c2 = search.charAt(index2);

			} else {
				break loop1;
			}

			while (true) {
				if (isEqual(c1, c2)) {

					if (index1 < subject.length() - 1
							&& index2 < search.length() - 1) {

						c1 = subject.charAt(++index1);
						c2 = search.charAt(++index2);
					} else if (index2 == search.length() - 1) {

						return fromIndex;
					} else {

						break loop1;
					}

				} else {

					index2 = 0;
					break;
				}
			}
			// ���²������ַ�����λ��
			index1 = ++fromIndex;
		}

		return -1;
	}

	/**
	 * ����ָ�����ַ����ڴ��ַ��������ұ߳��ִ���������
	 * 
	 * @param subject
	 *            �������ַ�����
	 * @param search
	 *            Ҫ���ҵ����ַ���
	 * @return �ڴ˶����ʾ���ַ����������һ�γ��ָ��ַ�������������ڸõ�֮ǰδ���ָ��ַ����򷵻� -1
	 */
	public static int ignoreCaseLastIndexOf(String subject, String search) {
		if (subject == null) {
			throw new NullPointerException("����Ĳ���Ϊ��");
		} else {
			return ignoreCaseLastIndexOf(subject, search, subject.length());
		}
	}

	/**
	 * ����ָ���ַ��ڴ��ַ��������һ�γ��ִ�����������ָ������������ʼ���з�����ҡ�
	 * 
	 * @param subject
	 *            �������ַ��� ��
	 * @param search
	 *            Ҫ���ҵ����ַ�����
	 * @param fromIndex
	 *            ��ʼ���ҵ�������fromIndex ��ֵû�����ơ���������ڵ��ڴ��ַ����ĳ��ȣ�������С�ڴ��ַ������ȼ� 1
	 *            ��Ч����ͬ�������������ַ����� �����Ϊ����������Ϊ -1 ��Ч����ͬ������ -1��
	 * @return �ڴ˶����ʾ���ַ����У�С�ڵ��� fromIndex�������һ�γ��ָ��ַ��������� ����ڸõ�֮ǰδ���ָ��ַ����򷵻� -1
	 */
	public static int ignoreCaseLastIndexOf(String subject, String search,
			int fromIndex) {

		// ���������ַ�����������ַ���Ϊ��ʱ���׳���ָ���쳣��
		if (subject == null || search == null) {
			throw new NullPointerException("����Ĳ���Ϊ��");
		}

		if (search.equals("")) {
			return fromIndex >= subject.length() ? subject.length() : fromIndex;
		}

		fromIndex = fromIndex >= subject.length() ? subject.length() - 1
				: fromIndex;

		int index1 = fromIndex;
		int index2 = 0;

		char c1;
		char c2;

		loop1: while (true) {

			if (index1 >= 0) {
				c1 = subject.charAt(index1);
				c2 = search.charAt(index2);
			} else {
				break loop1;
			}

			while (true) {
				// �ж������ַ��Ƿ����
				if (isEqual(c1, c2)) {
					if (index1 < subject.length() - 1
							&& index2 < search.length() - 1) {

						c1 = subject.charAt(++index1);
						c2 = search.charAt(++index2);
					} else if (index2 == search.length() - 1) {

						return fromIndex;
					} else {

						break loop1;
					}
				} else {
					// �ڱȽ�ʱ�����ֲ������ַ�����ĳ���ַ���ƥ�䣬�����¿�ʼ�������ַ���
					index2 = 0;
					break;
				}
			}
			// ���²������ַ�����λ��
			index1 = --fromIndex;
		}

		return -1;
	}

	/**
	 * �ж������ַ��Ƿ���ȡ�
	 * 
	 * @param c1
	 *            �ַ�1
	 * @param c2
	 *            �ַ�2
	 * @return ����Ӣ����ĸ�������ִ�Сд�����true�����ȷ���false�� �����������֣���ȷ���true�����ȷ���false��
	 */
	private static boolean isEqual(char c1, char c2) {
		// ��ĸСд ��ĸ��д
		if (((97 <= c1 && c1 <= 122) || (65 <= c1 && c1 <= 90))
				&& ((97 <= c2 && c2 <= 122) || (65 <= c2 && c2 <= 90))
				&& ((c1 - c2 == 32) || (c2 - c1 == 32))) {

			return true;
		} else if (c1 == c2) {
			return true;
		}

		return false;
	}

}
