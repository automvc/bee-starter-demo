//package com.automvc.fs;
//
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.InputStream;
//import java.nio.charset.StandardCharsets;
//import java.util.function.Consumer;
//
//import org.bson.Document;
//import org.bson.conversions.Bson;
//import org.bson.types.ObjectId;
//import org.teasoft.beex.mongodb.ds.SingleMongodbFactory;
//
//import com.mongodb.client.MongoDatabase;
//import com.mongodb.client.gridfs.GridFSBucket;
//import com.mongodb.client.gridfs.GridFSBuckets;
//import com.mongodb.client.gridfs.GridFSDownloadStream;
//import com.mongodb.client.gridfs.model.GridFSDownloadOptions;
//import com.mongodb.client.gridfs.model.GridFSFile;
//import com.mongodb.client.gridfs.model.GridFSUploadOptions;
//import com.mongodb.client.model.Filters;
//import com.mongodb.client.model.Sorts;
//
//public class MongoFsTest {
//	
//	public static void main(String[] args) throws Exception{
//		
//		MongoDatabase database= SingleMongodbFactory.getMongoDb();  //单个数据源时,
//		GridFSBucket gridFSBucket = GridFSBuckets.create(database);
//		
//		ObjectId fileId;
//		
////		String filePath = "F:\\temp\\0815日本投降.mp4";
////		try (InputStream streamToUploadFrom = new FileInputStream(filePath) ) {
////		    GridFSUploadOptions options = new GridFSUploadOptions()
////		            .chunkSizeBytes(1048576)
////		            .metadata(new Document("type", "mp4"));
////		    
////		    //同一个名字，可以重复保存，但ObjectId fileId不一样。
////		    fileId = gridFSBucket.uploadFromStream("0815日本投降.mp4", streamToUploadFrom, options);
////		    System.out.println("The file id of the uploaded file is: " + fileId.toHexString());
////		}
//		
//	
////		filePath = "F:\\temp\\testa.sql";
////		try (InputStream streamToUploadFrom = new FileInputStream(filePath) ) {
////		    GridFSUploadOptions options = new GridFSUploadOptions()
////		            .chunkSizeBytes(1048576)
////		            .metadata(new Document("type", "sql script"));
////		    
////		    //同一个名字，可以重复保存，但ObjectId fileId不一样。
////		    fileId = gridFSBucket.uploadFromStream("myProject.zip", streamToUploadFrom, options);
////		    System.out.println("The file id of the uploaded file is: " + fileId.toHexString());
////		}
//		
//		
//		
//		gridFSBucket.find().forEach(new Consumer<GridFSFile>() {
//		    @Override
//		    public void accept(final GridFSFile gridFSFile) {
//		        System.out.println(gridFSFile);
//		    }
//		});
//		
//		//filename是否可以
////		GridFSFile{id=BsonObjectId{value=63f46014f6a1f67e6eb5fb90}, filename='myProject.zip', length=118958, chunkSize=1048576, uploadDate=Tue Feb 21 14:09:24 CST 2023, metadata=Document{{type=zip archive}}}
////		GridFSFile{id=BsonObjectId{value=63f4679362f39248b7778386}, filename='myProject.zip', length=118958, chunkSize=1048576, uploadDate=Tue Feb 21 14:41:23 CST 2023, metadata=Document{{type=zip archive}}}
//
//		
//		System.out.println("---------------------------------");
//		
//		
////		Bson query = Filters.eq("metadata.type", "mp4");  //自定义的元数据metadata，都会放到metadata这个字段（JSON)
//		
//		Bson query = Filters.eq("filename", "0815日本投降.mp4"); 
//		
//		Bson sort = Sorts.ascending("filename");
//		gridFSBucket.find(query)
//		        .sort(sort)
//		        .limit(5)
//		        .forEach(new Consumer<GridFSFile>() {
//		            @Override
//		            public void accept(final GridFSFile gridFSFile) {
//		                System.out.println(gridFSFile);
//		            }
//		        });
//		
//		System.out.println("---------------------------------");
//		
//		
////		GridFSDownloadOptions downloadOptions = new GridFSDownloadOptions().revision(2);//同名文件，修订的版本号
////		try (FileOutputStream streamToDownloadTo = new FileOutputStream("F:\\temp\\uvf2.zip")) { //下载下来，生成的文件名
////		    gridFSBucket.downloadToStream("myProject.zip", streamToDownloadTo, downloadOptions); //无返回值
////		    streamToDownloadTo.flush();
////		}
////		
////		
////		System.out.println("-----------Download a File By  ObjectId to an Input Stream----------------------");
//////		Download a File to an Input Stream
//////		ObjectId fileId = new ObjectId("63f46014f6a1f67e6eb5fb90");
////		ObjectId fileId0 = fileId;
////		try (GridFSDownloadStream downloadStream = gridFSBucket.openDownloadStream(fileId0)) {  //返回的是files里面的id,查询时也是里面的id
////		    int fileLength = (int) downloadStream.getGridFSFile().getLength();
////		    byte[] bytesToWriteTo = new byte[fileLength];
////		    downloadStream.read(bytesToWriteTo);
////		    System.out.println(new String(bytesToWriteTo, StandardCharsets.UTF_8));
////		}
//		
//		
////		ObjectId fileId2 = new ObjectId("63f46014f6a1f67e6eb5fb90");
////		gridFSBucket.rename(fileId2, "mongodbTutorial.zip");
//		
////		ObjectId fileId = new ObjectId("60345d38ebfcf47030e81cc9");
////		gridFSBucket.delete(fileId);
////		
////		Delete a GridFS Bucket
////		MongoDatabase database = mongoClient.getDatabase("mydb");
////		GridFSBucket gridFSBucket = GridFSBuckets.create(database);
////		gridFSBucket.drop();
//		
////		GridFSUploadOptions 
////		GridFSDownloadOptions 
//		
//		
////		网格FS的工作原理
////		GridFS在一个存储桶中组织文件，这是一组MongoDB集合。 包含文件块和描述它们的信息。这 存储桶包含以下集合，使用约定命名 在 GridFS 规范中定义：
////
////		The chunks    collection stores the binary file chunks.
////		The files     collection stores the file metadata.
////		集合存储二进制文件块。chunks
////		集合存储文件元数据。files
////
////		创建新的 GridFS 存储桶时，驱动程序会创建前面的 集合，以默认存储桶名称为前缀，除非 指定其他名称。驱动程序还会在每个 收集以确保有效检索文件和相关 元数据。驱动程序仅在第一次写入时创建 GridFS 存储桶 操作（如果尚不存在）。驱动程序仅在以下情况下创建索引 它们不存在，当存储桶为空时。欲了解更多信息 GridFS 索引，请参阅服务器上的服务器手册页fs
////		网格FS 索引。
////
////		使用 GridFS 存储文件时，驱动程序会将文件拆分为较小的文件 块，每个块由集合中的单独文档表示。 它还在集合中创建一个包含以下内容的文档 文件 ID、文件名和其他文件元数据。您可以从以下位置上传文件 内存或来自流。请参阅下图以了解 GridFS 如何拆分 上传到存储桶时的文件。chunksfiles
//	
//	
////		When retrieving files, GridFS fetches the metadata from the files collection in the specified bucket and uses the information to reconstruct the file from documents in the chunks collection. 
////		You can read the file into memory or output it to a stream.
////		检索文件时，GridFS 从指定存储桶中的集合中提取元数据，并使用这些信息重建 集合中文档中的文件。您可以读取文件 到内存中或将其输出到流中。fileschunks
//	
//	
//	}
//
//}
