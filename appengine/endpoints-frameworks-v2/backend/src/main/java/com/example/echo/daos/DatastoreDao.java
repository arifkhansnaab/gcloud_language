
/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.echo.daos;

import com.example.echo.Result;
import com.example.echo.Word;

import com.google.cloud.datastore.Cursor;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.KeyFactory;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;

import java.util.ArrayList;
import java.util.List;

// [START example]
public class DatastoreDao implements WordDao {
  private Datastore datastore;
  private KeyFactory keyFactory;

  public DatastoreDao() {
    datastore = DatastoreOptions.getDefaultInstance().getService(); // Authorized Datastore service
    keyFactory = datastore.newKeyFactory().setKind("words");      // Is used for creating keys later
  }

  @Override
  public Result<Word> listBooks(String startCursorString) {
    Cursor startCursor = null;
    if (startCursorString != null && !startCursorString.equals("")) {
      startCursor = Cursor.fromUrlSafe(startCursorString);    // Where we left off
    }
    Query<Entity> query = Query.newEntityQueryBuilder()       // Build the Query
        .setKind("words")                                     // We only care about Books
        .setLimit(10)                                         // Only show 10 at a time
        .setStartCursor(startCursor)                          // Where we left off
        //.setOrderBy("test")                                 // Use default Index "title"
        .build();
    QueryResults<Entity> resultList = datastore.run(query);   // Run the query
    List<Word> resultWords = entitiesToWords(resultList);     // Retrieve and convert Entities
    Cursor cursor = resultList.getCursorAfter();              // Where to start next time
    if (cursor != null && resultWords.size() == 10) {         // Are we paging? Save Cursor
      String cursorString = cursor.toUrlSafe();               // Cursors are WebSafe
      return new Result<>(resultWords, cursorString);
    } else {
      return new Result<>(resultWords);
    }
  }

  public List<Word> entitiesToWords(QueryResults<Entity> resultList) {
    List<Word> resultWords = new ArrayList<>();

    while (resultList.hasNext()) {  // We still have data
      resultWords.add(entityToWord(resultList.next()));      // Add the Book to the List
    }
    return resultWords;
  }

  public Word entityToWord(Entity entity) {
    return new Word.Builder()                                     // Convert to Book form
      .englishWord(entity.getString(Word.ENGLISHWORD))
      .romanUrdu(entity.getString(Word.ROMANDURDU))
      .publishedDate(entity.getString(Word.PUBLISHEDDATE))
      .build();
  }
}


