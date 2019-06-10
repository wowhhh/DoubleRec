package com.wyb.rec.CollaborationFiltering;
/**
 * Ƥ��ѷָ���ļ��㣬���������û�֮�����ƶȵ�
 * @author wyb
 *
 */
public class Pearson {
	public static double Sum(double[] arr){
		double total=(double)0.0;
		for(double ele:arr)
			total+=ele;
		return total;
	}
	public static double Mutipl(double[] arr1,double[] arr2,int len){
		double total=(double)0.0;
		for(int i=0;i<len;i++)
			total+=arr1[i]*arr2[i];
		return total;
	}
	public static double Pearson(double[] x,double[] y){
		int lenx=x.length;//x����
		int leny=y.length;//y����
		int len=lenx;//С�ݴ�
		if(lenx<leny) len=lenx;//len��Ϊ����С��
		else len=leny;	
		double sumX=Sum(x);//x�ܺ�
		double sumY=Sum(y);//y�ܺ�
		double sumXX=Mutipl(x,x,len);//x^2
		double sumYY=Mutipl(y,y,len);//y^2
		double sumXY=Mutipl(x,y,len);//x*y
		double upside=sumXY-sumX*sumY/len;
		//double downside=(double) Math.sqrt((sumXX-(Math.pow(sumX, 2))/len)*(sumYY-(Math.pow(sumY, 2))/len));
		double downside=(double) Math.sqrt((sumXX-Math.pow(sumX, 2)/len)*(sumYY-Math.pow(sumY, 2)/len));
		
		//System.out.println(len+" "+sumX+" "+sumY+" "+sumXX+" "+sumYY+" "+sumXY);
		return upside/downside;
	}
}
