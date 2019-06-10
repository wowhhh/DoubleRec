package com.wyb.rec.CollaborationFiltering;
import java.util.HashMap;
import java.util.List;
/**
 * �ص�
 * @author wyb
 *
 */
import java.util.Map;
import java.util.function.Consumer;
public class CollaborativeFiltering {
		/**
		 	* �����û���Эͬ���˵��Ƽ����
		 	* @param userIdList �û�id�б�
		 	* @param userKNNMatrix �û�KNN����
		 	* @param user2songRatingMatrix �û�-�������־���
		 	* @param songIdList ����id�б�
		 	* @param n �Ƽ���ǰn�׸���
		 	* @return ���û��ĸ����Ƽ��Ľ��  userId,[recSongidId1,recSongidId2]
		 	* 
		*/
	public static void test()
	{
		
	}
	//Ҫ�Ȳ�ѯ���û���id����������Ϊ�û��Ƽ���һά���󣬴˷������û��赥���Ƽ�
	public static Integer[] singleUserRec(List<Integer> userIdList,
			final Map<Integer,Integer[]> userKNNMatrix,final Map<Integer, double[]> user2songRatingMatrix,
		final List<Integer> songIdList,final int n,int curUserId)
	{
		// TODO Auto-generated method stub
		Integer[] knnIdArray=userKNNMatrix.get(curUserId);//����������������Ŀ���û��Ľ����û���id
		/**
		 * ����ÿһ�׵�ǰ�û�û�������ĸ���
		 * Эͬ�÷֣���k��������û��Ըø��������ֵľۺ�
		 */
		//����һ�������洢Ŀ���û������и������ֵ�����
		double[] curUserRatings=user2songRatingMatrix.get(curUserId);
		//����һ������Ŀ���û���������С��
		MininumHeap mininumHeap=new MininumHeap(n);
		//ѭ������Ŀ���û�û�������ĸ���
		for(int i=1;i<curUserRatings.length;i++)
		{
			/**�������޷�ֱ��==�ȴ�С**/
			if(curUserRatings[i]<0.01f)//���Ŀ���û��Դ˸���û�й���Ϊ
			{
				//ѭ�������ڽ��û�
				for(int knnIndex=0;knnIndex<knnIdArray.length;knnIndex++)
				{
					int knnId=knnIdArray[knnIndex];//��ȡ�����û����û��б��е�id
					double[] knnUserRatings=user2songRatingMatrix.get(knnId);//��ȡ�����û������и���������
					curUserRatings[i]+=knnUserRatings[i];//���ұ����ܷ�
				}
				//�ּܷ������֮�󣬼���ƽ��ֵ��ΪԤ��
				curUserRatings[i]/=knnIdArray.length;
				int curSongId=songIdList.get(i-1);
				//���뵽����
				mininumHeap.addElement(new TreeNode(curSongId,curUserRatings[i]));
			}
		}
		/**
		 * �Ը��û�û�������ĸ�����Эͬ�÷���ɣ�ѡȡn����Ϊ��ߵ���Ŀ�����Ƽ�
		 */
		int trueNumber=n;
		//����Ƽ�����Ŀ���ڼƻ��Ƽ�����Ŀ������ĸ���������٣�
		if(mininumHeap.getCurHeapSize()<n)
		{
			trueNumber=mininumHeap.getCurHeapSize();
		}
		//�����Ƽ�����id�����飬��СΪtruenumber
		Integer[] curUserRecSongId=new Integer[trueNumber];
		for(int i=0;i<trueNumber;i++)
		{
			int recSongId=mininumHeap.getArray()[i].id;//��ȡ�Ƽ��ĸ�����id
			curUserRecSongId[i]=recSongId;
		}
		
		//���û�id�Լ����󣬲��뼴��
		
		return curUserRecSongId;
		
		//�˴�����ֱ�ӱ��浽���鵱��
	}
	
	//ֻ���㵥���û����Ƽ�����������û���id
	public static Map<Integer,Integer[]> userKNNBasedCF( List<Integer> userIdList,
			final Map<Integer,Integer[]> userKNNMatrix,final Map<Integer, double[]> user2songRatingMatrix,
		final List<Integer> songIdList,final int n	)
	{
		//�����û����Ƽ������ľ���,����:[�û�id] [�Ƽ�����1���Ƽ�����2���Ƽ�����3]
		final Map<Integer, Integer[]> user2songRecMatrix=new HashMap<Integer,Integer[]>();
		
		//�������е��û�id�����б���
		userIdList.forEach(new Consumer<Integer>() {

			@Override
			public void accept(Integer curUserId) {
				// TODO Auto-generated method stub
				Integer[] knnIdArray=userKNNMatrix.get(curUserId);//����������������Ŀ���û��Ľ����û���id
				/**
				 * ����ÿһ�׵�ǰ�û�û�������ĸ���
				 * Эͬ�÷֣���k��������û��Ըø��������ֵľۺ�
				 */
				//����һ�������洢Ŀ���û������и������ֵ�����
				double[] curUserRatings=user2songRatingMatrix.get(curUserId);
				//����һ������Ŀ���û���������С��
				MininumHeap mininumHeap=new MininumHeap(n);
				//ѭ������Ŀ���û�û�������ĸ���
				for(int i=1;i<curUserRatings.length;i++)
				{
					/**�������޷�ֱ��==�ȴ�С**/
					if(curUserRatings[i]<0.01f)//���Ŀ���û��Դ˸���û�й���Ϊ
					{
						//ѭ�������ڽ��û�
						for(int knnIndex=0;knnIndex<knnIdArray.length;knnIndex++)
						{
							int knnId=knnIdArray[knnIndex];//��ȡ�����û����û��б��е�id
							double[] knnUserRatings=user2songRatingMatrix.get(knnId);//��ȡ�����û������и���������
							curUserRatings[i]+=knnUserRatings[i];//���ұ����ܷ�
						}
						//�ּܷ������֮�󣬼���ƽ��ֵ��ΪԤ��
						curUserRatings[i]/=knnIdArray.length;
						int curSongId=songIdList.get(i-1);
						//���뵽����
						mininumHeap.addElement(new TreeNode(curSongId,curUserRatings[i]));
					}
				}
				/**
				 * �Ը��û�û�������ĸ�����Эͬ�÷���ɣ�ѡȡn����Ϊ��ߵ���Ŀ�����Ƽ�
				 */
				int trueNumber=n;
				//����Ƽ�����Ŀ���ڼƻ��Ƽ�����Ŀ������ĸ���������٣�
				if(mininumHeap.getCurHeapSize()<n)
				{
					trueNumber=mininumHeap.getCurHeapSize();
				}
				//�����Ƽ�����id�����飬��СΪtruenumber
				Integer[] curUserRecSongId=new Integer[trueNumber];
				for(int i=0;i<trueNumber;i++)
				{
					int recSongId=mininumHeap.getArray()[i].id;//��ȡ�Ƽ��ĸ�����id
					curUserRecSongId[i]=recSongId;
				}
				user2songRecMatrix.put(curUserId,curUserRecSongId);
			}
		});
		
		return user2songRecMatrix;
	}
}
