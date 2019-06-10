package com.wyb.rec.CollaborationFiltering;

public class Similarity {

	public static double calculateSimilarity(double[] curRating, double[] otherRating) {
		// TODO Auto-generated method stub
		double similarity=0f;
		/*int len=curRating.length;
		int cnt=0;
		for(int i=0;i<len;i++) {
			//���ݱ���ǰ�û������֡��˵���Ŀ���������ƶȣ����ڸ���������==�Ƚϣ�������0.01f��ʾ0.0f
			if(curRating[i]>0.01f) {
				similarity+=Math.pow(curRating[i]-otherRating[i], 2);
				cnt++;
			}
		}
		similarity/=(cnt>0?cnt:1);*/
		
		similarity=Pearson.Pearson(curRating, otherRating);
		
		return similarity;
	}
}
