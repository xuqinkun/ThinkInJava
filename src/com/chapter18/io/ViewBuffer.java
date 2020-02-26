package com.chapter18.io;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.FloatBuffer;

/**
 * ��ͬһ��ByteBuffer�Ͻ�����ͬ����ͼ��������
 * ��ͬһ���ֽ����з����short��int��float��
 * long��double���͵�����
 * @author xqk
 * @date 2017��8��9�� ����10:18:46 
 * @version V1.0
 */
public class ViewBuffer {
	public static void main(String[] args) {
		ByteBuffer bb = ByteBuffer.wrap(
				new byte[]{0,0,0,0,0,0,0,'a'});
		bb.rewind();
		System.out.print("Byte Buffer ");
		while(bb.hasRemaining())
			System.out.print(bb.position() + " -> " + bb.get() + ", ");
		System.out.println();
		
		CharBuffer cb = ((ByteBuffer)bb.rewind()).asCharBuffer();
		System.out.print("Char Buffer ");
		while(cb.hasRemaining())
			System.out.print(cb.position() + " -> " + cb.get() + ", ");
		System.out.println();
		
		FloatBuffer fb = 
				((ByteBuffer)bb.rewind()).asFloatBuffer();
		System.out.print("Float Buffer: ");
		while(fb.hasRemaining())
			System.out.print(fb.position() + " -> " + fb.get() + ", ");
		System.out.println();
	}
}
