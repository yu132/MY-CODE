package data_structures.list;

import data_structures.list.interfaces.LinearList;

public class MyLinkedList<E> implements LinearList<E>{

	private Node<E> head;
	
	@Override
	public boolean isEmpty() {
		if(head==null)
			return true;
		else
			return false;
	}

	@Override
	public int size() {
		if(head!=null){
			int count=1;
			Node<E> node=head.next;
			while(node!=null){
				count++;
				node=node.next;
			}
			return count;
		}else
			return 0;
	}

	@Override
	public E get(int index) {
		if(index<0)
			throw new IndexOutOfBoundsException();
		if(head!=null){
			if(index==0)
				return head.data;
			Node<E> node=head.next;
			while((--index)!=0&&node!=null){
				node=node.next;
			}
			if(node==null)
				throw new IndexOutOfBoundsException();
			else
				return node.data;
		}else
			return null;
	}

	@Override
	public boolean delete(E element) {
		if(head!=null){
			if(head.data.equals(element)){
				head=head.next;
				return true;
			}
			Node<E> node=head;
			while(node!=null){
				if(node.next.data.equals(element)){
					node.next=node.next.next;
					return true;
				}
				node=node.next;
			}
			return false;
		}else
			return false;
	}

	@Override
	public int indexOf(E element) {
		if(head!=null){
			if(head.data.equals(element)){
				return 0;
			}
			Node<E> node=head.next;
			int count=1;
			while(node!=null){
				if(node.data.equals(element)){
					return count;
				}
				node=node.next;
				count++;
			}
			return -1;
		}else
			return -1;
	}

	@Override
	public E insert(int index, E e) {
		if(index<0)
			throw new IndexOutOfBoundsException();
		if(index==0){
			Node<E> temp=head;
			head=new Node<E>(e,temp);
			return e;
		}
		if(head!=null){
			Node<E> node=head;
			while((--index)!=0&&node!=null){
				node=node.next;
			}
			if(node==null)
				throw new IndexOutOfBoundsException();
			else{
				Node<E> temp=node.next;
				node.next=new Node<E>(e,temp);
				return e;
			}
		}else
			return null;
	}

	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder(100);
		Node<E> node=head;
		sb.append("[");
		while(node.next!=null){
			sb.append(node.data+",");
			node=node.next;
		}
		sb.append(node.data+"]");
		return sb.toString();
	}

	static class Node<E>{
		private E data;
		private Node<E> next;
		public Node(E data, Node<E> next) {
			super();
			this.data = data;
			this.next = next;
		}
	}
	
/*	public static void main(String[] args) {
		MyLinkedList<Integer> list=new MyLinkedList<>();
		list.insert(0, 1);
		list.print();
		list.insert(1, 4);
		list.print();
		list.insert(0, 2);
		list.print();
		list.insert(1, 5);
		list.print();
		list.insert(0, 7);
		list.print();
		list.insert(1, 9);
		list.print();
		//list.print();
		System.out.println(list.indexOf(2));
		list.delete(2);
		list.print();
		System.out.println(list.get(4));
	}*/
	
}
