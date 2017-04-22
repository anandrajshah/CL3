
#include <iostream>
#include<stdio.h>
#include<stdlib.h>
#include <omp.h>
#include<fstream>

int k=0;
using namespace std;

class Sort
{
	public:
	void quick_sort(int a[],int first,int last);
	int partition(int a[],int first,int last);

};

int Sort::partition(int a[],int first,int last)
{
	int i,j,pivot,temp;
	pivot=a[first];
	i=first;
	j=last;
	while(1)
	{
		while(a[i]<pivot && a[i]!=pivot)
		{
			i++;
		}
		while(a[j]>pivot && a[j]!=pivot)
		{
			j--;
		}
		if(i<j)
		{
			temp=a[i];
			a[i]=a[j];
			a[j]=temp;
		}
		else
		{
			return j;
		}
	}
}

void Sort::quick_sort(int a[],int first,int last)
{
	if(first<last)
	{
		int q;
		q=partition(a,first,last);
		cout<<"\nPivot element with index "<<q<<" has been find out by thread "<<k<<"\n";
		#pragma omp parallel sections
		{
			#pragma omp section
			{
				k=k+1;
				quick_sort(a,first,q);
			}
			#pragma omp section
			{
				k=k+1;
				quick_sort(a,q+1,last);
			}
		}
	}
}

int main() 
{
	int first,last;
	int n=0, l=0, i=0, N=0;
	int a[50];
	string line,tmp;
	bool begin_tag = false;
	bool begin_tag1 = false;

	ifstream in("input.xml");

	while (getline(in,line))
	{
		tmp="";
		for (int i = 0; i < line.length(); i++)
		{
			if (line[i] == ' ' && tmp.size() == 0)
			{  }
			else
			{
				tmp += line[i];
			}
		}
		if (tmp == "<number>")
		{
			begin_tag1 = true;
			continue;
		}
		else if (tmp == "</number>")
		{
			begin_tag1 = false;
		}
		if (begin_tag1)
		{
			n++;
			N=atoi(tmp.c_str());
			a[l]=N;
			l++;
		}	
	}

	n=l;
	first=0;
	last=n-1;
	
	Sort s;
	s.quick_sort(a,first,last);

	cout<<"\nSorted array is :\t";
	for(int i=0;i<n;i++)
	{
		cout<<a[i]<<"\t";
	}
	cout<<"\n\n";
	return 0;
}
