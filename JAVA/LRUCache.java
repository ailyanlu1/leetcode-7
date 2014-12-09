package bingyue.leetcode;

import java.util.HashMap;

/**
 * Design and implement a data structure for Least Recently Used (LRU) cache. 
 * It should support the following operations: get and set.
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity,
 *  it should invalidate the least recently used item before inserting a new item.
 *  近期最少使用算法 设计缓存
 */
public class LRUCache {
	
	private int cacheSize;//缓存容量
	private int currentSize;//当前容量
	private HashMap<Object, CacheNode> nodes;//缓存容器
	private CacheNode head;//链表头
	private CacheNode last;//链表尾

	class CacheNode{
		CacheNode prev;//前一节点
		CacheNode next;//后一节点
		int value;//值
		int key;//键
		CacheNode() {
		}
	}
	
	//初始化缓存
	public LRUCache(int capacity) {
		currentSize=0;
		cacheSize=capacity;
		nodes=new HashMap<Object, CacheNode>(capacity);
		
	}
	    
	public Integer get(int key) {
		CacheNode node = nodes.get(key);
		if (node != null) {
			move(node);
			return node.value;
		} else {
			return -1;//error code
		}
	        
	}
	    
	public void set(int key, int value) {
		CacheNode node = nodes.get(key);
		//重复Key
		if(node!=null){
			node.value=value;
			move(node);
			nodes.put(key, node);
		}else
		   {//key未重复，正常流程
			node =new CacheNode();
			if(currentSize>=cacheSize){
				if (last != null){//缓存已满，进行淘汰
					nodes.remove(last.key);}
				removeLast();//移除链表尾部并后移	
			}else{
				currentSize++;
			}
			
			node.key=key;
			node.value=value;
			move(node);
			nodes.put(key, node);
		}
	}
	
	//移动链表节点至头部
	private void move(CacheNode cacheNode){
		if(cacheNode==head)
			return;
		//链接前后节点
		if(cacheNode.prev!=null)
			cacheNode.prev.next=cacheNode.next;
		if(cacheNode.next!=null)
			cacheNode.next.prev=cacheNode.prev;
		//头尾节点
		if (last == cacheNode)
			last = cacheNode.prev;
		if (head != null) {
			cacheNode.next = head;
			head.prev = cacheNode;
		}
		//移动后的链表
		head = cacheNode;
		cacheNode.prev = null;
		//节点唯一的情况
		if (last == null)
			last = head;
	}
	
	//移除指定缓存
	public void remove(int key){
		CacheNode cacheNode =  nodes.get(key);
		if (cacheNode != null) {
			if (cacheNode.prev != null) {
				cacheNode.prev.next = cacheNode.next;
			}
			if (cacheNode.next != null) {
				cacheNode.next.prev = cacheNode.prev;
			}
			if (last == cacheNode)
				last = cacheNode.prev;
			if (head == cacheNode)
				head = cacheNode.next;
		}
		
	}
	//删除尾部的结点，即去除最近最久未使用数据
	private void removeLast(){
		if(last!=null){
			if(last.prev!=null){
				last.prev.next=null;				
			}else{//空间大小为1的情况
				head = null;				
			}
			last = last.prev;
		}
	}
	
	public void clear() {
		head = null;
		last = null;
	}
	//测试用例
//	public static void main(String[] args){
//		LRUCache lCache=new LRUCache(2);
//		lCache.set(2, 1);
//		lCache.set(1, 1);
//		lCache.set(2, 3);
//		lCache.set(4, 1);
//		System.out.println(lCache.get(1));
//		System.out.println(lCache.get(2));
//		
//	}

}
