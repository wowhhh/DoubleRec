package com.wyb.rec.CollaborationFiltering;
/**
 * С����
 * @author wyb
 *
 */
public class MininumHeap {
	
	private int curHeapSize;//��ǰ�ѵĴ�С
	private TreeNode[] array;//������
	private boolean isFirstTime=true;//�Ƿ�Ϊ��һ�δ�����

	public MininumHeap() {
	}//���������Ĺ��췽��

	public MininumHeap(int heapSize) {
		array = new TreeNode[heapSize];
	}//��������Ĺ��췽����Ϊ������ȥָ����С

	/**
	 * ������Ԫ�ز����ֶѵĴ�С����.
	 * ����ǰԪ��С����С�ѵĶѶ���ʱ�򣬲�����;
	 * ��֮���Ƴ���Ԫ�أ����뵱ǰԪ�أ���������.
	 * @param treeNode
	 * ��Ҫ�����Ԫ��
	 */
	public void addElement(TreeNode treeNode) {
		//����ѻ�û�б������Ļ�����ֱ�Ӽ���
		if(curHeapSize<array.length) {
			array[curHeapSize++]=treeNode;
		}else {
			//������ʱ�򣬽��н���
			if(isFirstTime) {
				for(int i=curHeapSize/2-1;i>=0;i--)	shiftdown(array,i,curHeapSize);
				isFirstTime=false;
			}
			//���Ѿ����ˣ�����Ҫ�Ƚ�һ��
			if(treeNode.val<=array[0].val) {
				//��ǰԪ�أ��ȶѶ�Ԫ�ػ�С��ֱ������
				return;
			}else {
				//�Ƴ��滻�Ѷ�Ԫ�أ�������
				array[0]=treeNode;
				shiftdown(array,0,curHeapSize);
			}
		}
		

	}

	private void shiftdown(TreeNode[] array, int position, int len) {
		while(position<=len/2-1){
			int l=2*position+1,r=2*position+2;
			if(r<len&&array[l].val>array[r].val)	l=r;
			if(array[l].val>=array[position].val)	 return;
			swap(array,l,position);
			position=l;//move down
		}
		}
	private void swap(TreeNode[] array, int i, int j) {
		// TODO Auto-generated method stub
		TreeNode temp=array[i];
		array[i]=array[j];
		array[j]=temp;
	}

	/**
	 * ��ȡ��Ԫ������
	 * @return
	 */
	public TreeNode[] getArray() {
		return array;
	}

	/**
	 * ��ȡ��ǰ�ѵ���ʵ��С
	 * @return
	 */
	public int getCurHeapSize() {
		return curHeapSize;
	}
}