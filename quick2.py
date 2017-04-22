import time
import sys,os
from threading import Thread
import thread

def swap(arr,left,right):
	temp=arr[left]
	arr[left]=arr[right]
	arr[right]=temp

def partition(arr,low,high):
	pivot=low
	pivot_item=arr[low]
	right=high
	left=low+1
	
	while True:
		while arr[left]<=pivot_item and left<high:
			left +=1
		while arr[right]>pivot_item:
			right -=1
		if left<right:
			swap(arr,left,right)
			#print arr
		else:
			temp= arr[low]
			arr[low] = arr[right] 
			arr[right]= temp 
			return right

def quicksort(arr,low,high):
	if low<high:
		pivot1=partition(arr,low,high)
		quicksort(arr,low,pivot1-1)
		quicksort(arr,pivot1+1,high)

arr=[]

from xml.dom import minidom
xmldoc = minidom.parse('input3.xml')
itemlist = xmldoc.getElementsByTagName('number') 

size=len(itemlist)
for s in itemlist :
    x=s.firstChild.nodeValue
    arr.append(int(x))

print "\n UnSorted array:"
print arr
start_time=time.time()
t=Thread(target=quicksort,args=(arr,0,size-1))
t.start()
#quicksort(arr,0,size-1)
print "\n Sorted array:"
print arr
print "\n Time Taken:\n",(time.time()-start_time)

