	//NAME:DEVINA DHURI
	//ROLL  NO:4325
	//ASSIGNMENT:DINING PHILOSOPHER	

	import com.mongodb.*;
	import com.mongodb.MongoClient;

	import java.util.concurrent.locks.Lock;
	import java.util.concurrent.locks.ReentrantLock;
	public class Dining {
	public static void main(String[] args) 
	{
		Lock forks[] = new ReentrantLock[5];
		try 
		{
			MongoClient mongoClient = new MongoClient("localhost");
			System.out.println("Connection to mongodb successful.");
			DB db = mongoClient.getDB( "mydb" );
			System.out.println("Database 'mydb' created.");
			DBCollection coll = db.createCollection("mycol2", null);
			System.out.println("Collection 'mycol' created.");
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 0; i<5; i++)
		{
			forks[i] = new ReentrantLock();
		}
		Thread p1 = new Thread(new Philosopher(forks[4], forks[0], "first"));
		Thread p2 = new Thread(new Philosopher(forks[0], forks[1], "second"));
		Thread p3 = new Thread(new Philosopher(forks[1], forks[2], "third"));
		Thread p4 = new Thread(new Philosopher(forks[2], forks[3], "fourth"));
		Thread p5 = new Thread(new Philosopher(forks[3], forks[4], "fifth"));

		p1.start();
		p2.start();
		p3.start();
		p4.start();
		p5.start();
		}
	}


	class Philosopher implements Runnable {
		Lock leftFork = new ReentrantLock();
		Lock rightFork = new ReentrantLock();
		String name;
		public Philosopher(Lock leftFork, Lock rightFork, String name) {
			this.leftFork = leftFork;
			this.rightFork = rightFork;
			this.name = name;
		}
		@Override
		public void run() {
			try {
				think(name);
				eat(leftFork, rightFork, name);
			} 
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		private void eat(Lock leftFork, Lock rightFork, String name) throws Exception{
			leftFork.lock();
			rightFork.lock();
			try
			{
				MongoClient mongoClient = new MongoClient("localhost");
				DB db = mongoClient.getDB( "mydb" );
				DBCollection coll = db.getCollection("mycol2");
				System.out.println(name + " eating...");
				BasicDBObject doc1 = new BasicDBObject(name , " eating...");
				coll.insert(doc1);
				Thread.sleep(1000);
			}
			 catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				System.out.println(name + " done eating and now thinking...");
				MongoClient mongoClient = new MongoClient("localhost");
				DB db = mongoClient.getDB( "mydb" );
				DBCollection coll = db.getCollection("mycol2");
				BasicDBObject doc2 = new BasicDBObject(name , " done eating and now thinking...");
				coll.insert(doc2);
				leftFork.unlock();
				rightFork.unlock();
			}
		}
		public void think(String name) throws Exception{
			try
			{
				MongoClient mongoClient = new MongoClient("localhost");
				DB db = mongoClient.getDB( "mydb" );
				DBCollection coll = db.getCollection("mycol2");
				System.out.println(name + " thinking...");
				BasicDBObject doc = new BasicDBObject(name , " thinking...");
				coll.insert(doc);
				Thread.sleep(1000);
			} 
			catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}




	/*
	 OUTPUT1: "TERMINAL":

	 Connection to mongodb successful.
	Database 'mydb' created.
	Collection 'mycol' created.
	first thinking...
	third thinking...
	fifth thinking...
	fourth thinking...
	second thinking...
	fifth eating...
	fifth done eating and now thinking...
	fourth eating...
	fourth done eating and now thinking...
	third eating...
	third done eating and now thinking...
	second eating...
	second done eating and now thinking...
	first eating...
	first done eating and now thinking...

	"DATABASE":

	[ccoew@localhost ~]$ cd ./mongodb-linux-x86_64-3.0.2/bin/
	[ccoew@localhost bin]$ ./mongo
	MongoDB shell version: 3.0.2
	connecting to: test

	> use mydb
	switched to db mydb

	> db.mycol1.find()
	{ "_id" : ObjectId("587606c145ce2240fa3b4e28"), "third" : " thinking..." }
	{ "_id" : ObjectId("587606c145ce2240fa3b4e25"), "fourth" : " thinking..." }
	{ "_id" : ObjectId("587606c145ce2240fa3b4e27"), "fifth" : " thinking..." }
	{ "_id" : ObjectId("587606c145ce2240fa3b4e26"), "first" : " thinking..." }
	{ "_id" : ObjectId("587606c145ce2240fa3b4e29"), "second" : " thinking..." }
	{ "_id" : ObjectId("587606c245ce2240fa3b4e2a"), "fifth" : " eating..." }
	{ "_id" : ObjectId("587606c345ce2240fa3b4e2b"), "fifth" : " done eating and now thinking..." }
	{ "_id" : ObjectId("587606c345ce2240fa3b4e2c"), "fourth" : " eating..." }
	{ "_id" : ObjectId("587606c445ce2240fa3b4e2d"), "fourth" : " done eating and now thinking..." }
	{ "_id" : ObjectId("587606c545ce2240fa3b4e2e"), "third" : " eating..." }
	{ "_id" : ObjectId("587606c645ce2240fa3b4e2f"), "third" : " done eating and now thinking..." }
	{ "_id" : ObjectId("587606c645ce2240fa3b4e30"), "second" : " eating..." }
	{ "_id" : ObjectId("587606c745ce2240fa3b4e31"), "second" : " done eating and now thinking..." }
	{ "_id" : ObjectId("587606c745ce2240fa3b4e32"), "first" : " eating..." }
	{ "_id" : ObjectId("587606c845ce2240fa3b4e33"), "first" : " done eating and now thinking..." }

	=====================================================================================================
	 OUTPUT2: "TERMINAL":

	third thinking...
	second thinking...
	first thinking...
	fourth thinking...
	fifth thinking...
	third eating...
	third done eating and now thinking...
	second eating...
	second done eating and now thinking...
	first eating...
	first done eating and now thinking...
	fifth eating...
	fifth done eating and now thinking...
	fourth eating...
	fourth done eating and now thinking...

	"DATABASE":

	[ccoew@localhost ~]$ cd ./mongodb-linux-x86_64-3.0.2/bin/
	[ccoew@localhost bin]$ ./mongo
	MongoDB shell version: 3.0.2
	connecting to: test
	> use mydb
	switched to db mydb

	> db.mycol1.find()
	{ "_id" : ObjectId("587606c145ce2240fa3b4e28"), "third" : " thinking..." }
	{ "_id" : ObjectId("587606c145ce2240fa3b4e25"), "second" : " thinking..." }
	{ "_id" : ObjectId("587606c145ce2240fa3b4e27"), "first" : " thinking..." }
	{ "_id" : ObjectId("587606c145ce2240fa3b4e26"), "fourth" : " thinking..." }
	{ "_id" : ObjectId("587606c145ce2240fa3b4e29"), "fifth" : " thinking..." }
	{ "_id" : ObjectId("587606c245ce2240fa3b4e2a"), "third" : " eating..." }
	{ "_id" : ObjectId("587606c345ce2240fa3b4e2b"), "third" : " done eating and now thinking..." }
	{ "_id" : ObjectId("587606c345ce2240fa3b4e2c"), "second" : " eating..." }
	{ "_id" : ObjectId("587606c445ce2240fa3b4e2d"), "second" : " done eating and now thinking..." }
	{ "_id" : ObjectId("587606c545ce2240fa3b4e2e"), "first" : " eating..." }
	{ "_id" : ObjectId("587606c645ce2240fa3b4e2f"), "first" : " done eating and now thinking..." }
	{ "_id" : ObjectId("587606c645ce2240fa3b4e30"), "fifth" : " eating..." }
	{ "_id" : ObjectId("587606c745ce2240fa3b4e31"), "fifth" : " done eating and now thinking..." }
	{ "_id" : ObjectId("587606c745ce2240fa3b4e32"), "fourth" : " eating..." }
	{ "_id" : ObjectId("587606c845ce2240fa3b4e33"), "fourth" : " done eating and now thinking..." }


	*/
