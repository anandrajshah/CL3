	# Book Application

	import csv
	import os
	alist=[]
	blist=[]

	with open('books.csv', 'rb') as csvfile:
		rreader = csv.reader(csvfile,delimiter=',')
		for row in rreader:					#row is a list
			r = ''.join(row[1])				
			blist.append(row[0])
			#print r
			alist.append(r.lower())

	def mergeSort(alist):
	    if len(alist)>1:
		mid = len(alist)//2
		lefthalf = alist[:mid]
		righthalf = alist[mid:]

		mergeSort(lefthalf)
		mergeSort(righthalf)

		i=0
		j=0
		k=0
		while i < len(lefthalf) and j < len(righthalf):
		    if lefthalf[i] < righthalf[j]:
			alist[k]=lefthalf[i]
			i=i+1
		    else:
			alist[k]=righthalf[j]
			j=j+1
		    k=k+1

		while i < len(lefthalf):
		    alist[k]=lefthalf[i]
		    i=i+1
		    k=k+1

		while j < len(righthalf):
		    alist[k]=righthalf[j]
		    j=j+1
		    k=k+1

	def binary(alist,left,right):
		#print "Left = ",left
		#print "Right = ",right
		if(left>right):
			print "Book records not found."
		elif((key>=alist[left]) and (key<=alist[right])):
			mid=(left+right)/2
			#print "Seperator:", mid
			if(key==alist[mid]):
				print "book found on shelf no. :", mid+1
			elif(key<alist[mid]):
				binary(alist,left,mid-1)
			elif(key>alist[mid]):
				binary(alist,mid+1,right)
		elif((key<alist[left]) or (key>alist[right])):
			print "book not found."

	i=0
	print"List of Book IDs and Book names:"
	while(i<len(alist)):
		print blist[i],",",alist[i]
		i+=1
	print"Search by :"
	print"1) Book ID"
	print"2) Book Name"
	ch=int(raw_input("What is your choice?"))
	print ch
	if ch==1:
		key=raw_input("Enter the Book ID : ")
		mergeSort(blist)
		n=len(blist)
		binary(blist,left=0,right=n-1)
		print"shelf no.","book ID"
		'''for i in range(0,len(blist)):
			print i+1,"\t",blist[i]'''
	elif ch==2:
		key=raw_input("Enter the book name : ").lower()
		mergeSort(alist)
		n=len(alist)
		binary(alist,left=0,right=n-1)
		print"shelf No. ","location"
		for i in range(0,len(alist)):
			print i+1,"\t",alist[i]



	'''
	Output:


	[ccoew@sl2-29 ~]$ python books.py
	List of Book IDs and Book names:
	6758 , harry potter
	4589 , inferno
	1456 , deception point
	2116 , one indian girl
	6500 , angels and daemons
	9911 , what young india wants
	1111 , the last symbol
	4633 , shantaram
	9908 , akashashi jadle naate
	7634 , half girlfriend
	5435 , digital fortress
	5647 , wings of fire
	9875 , ignited minds
	5433 , half blood prince
	3784 , fault in our stars
	3874 , hunger games
	9879 , gone girl
	7623 , two states
	7490 , of mice and men
	8974 , kimayagaar
	8733 , mrutyunjay
	2367 , musafir
	8722 , kaajalmaya
	7634 , raja shiv chhatrapati
	3289 , chhaava
	4384 , alchemist
	2833 , secret
	7902 , da vinci code
	2633 , rich dad poor dad
	4523 , shyaamchi aai
	Search by :
	1) Book ID
	2) Book Name
	What is your choice?1
	1
	Enter the Book ID : 2833
	book found on shelf no. : 6
	shelf no. book ID
	[ccoew@sl2-29 ~]$ python books.py
	List of Book IDs and Book names:
	6758 , harry potter
	4589 , inferno
	1456 , deception point
	2116 , one indian girl
	6500 , angels and daemons
	9911 , what young india wants
	1111 , the last symbol
	4633 , shantaram
	9908 , akashashi jadle naate
	7634 , half girlfriend
	5435 , digital fortress
	5647 , wings of fire
	9875 , ignited minds
	5433 , half blood prince
	3784 , fault in our stars
	3874 , hunger games
	9879 , gone girl
	7623 , two states
	7490 , of mice and men
	8974 , kimayagaar
	8733 , mrutyunjay
	2367 , musafir
	8722 , kaajalmaya
	7634 , raja shiv chhatrapati
	3289 , chhaava
	4384 , alchemist
	2833 , secret
	7902 , da vinci code
	2633 , rich dad poor dad
	4523 , shyaamchi aai
	Search by :
	1) Book ID
	2) Book Name
	What is your choice?2
	2
	Enter the book name : alchemist
	book found on shelf no. : 2
	shelf No.  location
	1 	akashashi jadle naate
	2 	alchemist
	3 	angels and daemons
	4 	chhaava
	5 	da vinci code
	6 	deception point
	7 	digital fortress
	8 	fault in our stars
	9 	gone girl
	10 	half blood prince
	11 	half girlfriend
	12 	harry potter
	13 	hunger games
	14 	ignited minds
	15 	inferno
	16 	kaajalmaya
	17 	kimayagaar
	18 	mrutyunjay
	19 	musafir
	20 	of mice and men
	21 	one indian girl
	22 	raja shiv chhatrapati
	23 	rich dad poor dad
	24 	secret
	25 	shantaram
	26 	shyaamchi aai
	27 	the last symbol
	28 	two states
	29 	what young india wants
	30 	wings of fire
	[ccoew@sl2-29 ~]$ 

	'''

