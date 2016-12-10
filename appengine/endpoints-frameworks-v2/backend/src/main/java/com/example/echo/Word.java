
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

package com.example.echo;

public class Word {
  // [START book]
  private String englishWord;
  private String romanUrdu;
  private String publishedDate;
  // [END book]
  // [START keys]
  public static final String ENGLISHWORD = "englishWord";
  public static final String ROMANDURDU = "romanUrdu";
  public static final String PUBLISHEDDATE = "publishedDate";
  // [END keys]
 // [START constructor]
  // We use a Builder pattern here to simplify and standardize construction of Book objects.
  private Word(Builder builder) {
    this.englishWord = builder.englishWord;
    this.romanUrdu = builder.romanUrdu;
    this.publishedDate = builder.publishedDate;
  }
  // [END constructor]
  // [START builder]
  public static class Builder {
    private String englishWord;
    private String romanUrdu;
    private String publishedDate;

    public Builder englishWord(String englishWord) {
      this.englishWord = englishWord;
      return this;
    }

    public Builder romanUrdu(String romanUrdu) {
      this.romanUrdu = romanUrdu;
      return this;
    }

    public Builder publishedDate(String publishedDate) {
      this.publishedDate = publishedDate;
      return this;
    }

    public Word build() {
      return new Word(this);
    }
  }

  public String getEnglishWord() {
    return englishWord;
  }

  public void setEnglishWord(String englishWord) {
    this.englishWord = englishWord;
  }

  public String getRomanUrdu() {
    return romanUrdu;
  }

  public void setRomanUrdu(String romanUrdu) {
    this.romanUrdu = romanUrdu;
  }

  public String getPublishedDate() {
    return publishedDate;
  }

  public void setPublishedDate(String publishedDate) {
    this.publishedDate = publishedDate;
  }

  // [END builder]
  @Override
  public String toString() {
    return
            "English Word: " + englishWord + ", Roman Urdu: " + romanUrdu + ", "
            + "Published date: " + publishedDate;
  }
}
// [END example]
