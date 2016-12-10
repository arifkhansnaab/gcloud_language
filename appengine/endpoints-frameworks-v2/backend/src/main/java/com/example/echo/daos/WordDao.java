/**
 * Created by Arif on 12/9/16.
 */

package com.example.echo.daos;

import com.example.echo.Result;
import com.example.echo.Word;

import java.sql.SQLException;

public interface WordDao {
  Result<Word> listBooks(String startCursor) throws SQLException;
}