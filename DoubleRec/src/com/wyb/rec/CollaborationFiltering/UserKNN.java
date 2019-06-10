package com.wyb.rec.CollaborationFiltering;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * ���������û����ھ���
 * @author wyb
 *
 */
public class UserKNN {

	/**
	 * ��ȡ�û���k�������û�
	 * @param userIdList
	 * �û�Id�б�
	 * @param user2songRatingMatrix
	 * �û�-���� �����֡�����
	 * @param k
	 * ����k,k���ھ�
	 * @return
	 * userId,[neighborAId,neighborBId...neighborKId]
	 */
	public static Map<Integer, Integer[]> getKNN(List<Integer> userIdList, final Map<Integer, double[]> user2songRatingMatrix, final int k) {
		// TODO Auto-generated method stub
		final Map<Integer,Integer[]> userKNNMatrix=new HashMap<Integer,Integer[]>();
		userIdList.forEach(new Consumer<Integer>() {

			public void accept(final Integer curUserId) {
				// TODO Auto-generated method stub
				Integer[] knnId=new Integer[k];
				//Ϊ�û�����һ����С�����������������k���ھ�
				final MininumHeap mininumHeap=new MininumHeap(k);
				//��ȡK Nearest Neighbors
				user2songRatingMatrix.forEach(new BiConsumer<Integer, double[]>() {

					public void accept(Integer otherUserId, double[] userRatingArray) {
						// TODO Auto-generated method stub
						//�ų��Լ�
						if(otherUserId!=curUserId) {
							//���㵱ǰ�û��������û���������
							double similarity=Similarity.calculateSimilarity(user2songRatingMatrix.get(curUserId),user2songRatingMatrix.get(otherUserId));
							//�������
							mininumHeap.addElement(new TreeNode(otherUserId,similarity));
						}
						
					}

				});
				//�Ӷ��л�ȡ����������k���ھ�
				for(int i=0;i<k;i++) {
					knnId[i]=mininumHeap.getArray()[i].id;
				}
				userKNNMatrix.put(curUserId, knnId);
			}
			
		});
		return userKNNMatrix;
	}
}
