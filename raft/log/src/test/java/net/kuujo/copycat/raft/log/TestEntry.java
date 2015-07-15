/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.kuujo.copycat.raft.log;

import net.kuujo.alleycat.Alleycat;
import net.kuujo.alleycat.SerializeWith;
import net.kuujo.alleycat.io.BufferInput;
import net.kuujo.alleycat.io.BufferOutput;
import net.kuujo.alleycat.util.ReferenceManager;

/**
 * Command entry.
 *
 * @author <a href="http://github.com/kuujo">Jordan Halterman</a>
 */
@SerializeWith(id=1000)
public class TestEntry extends Entry<TestEntry> {
  private long term;
  private long request;
  private long response;

  public TestEntry(ReferenceManager<Entry<?>> referenceManager) {
    super(referenceManager);
  }

  /**
   * Returns the entry term.
   *
   * @return The entry term.
   */
  public long getTerm() {
    return term;
  }

  /**
   * Sets the entry term.
   *
   * @param term The entry term.
   * @return The entry.
   */
  @SuppressWarnings("unchecked")
  public TestEntry setTerm(long term) {
    this.term = term;
    return this;
  }

  /**
   * Returns the command request number.
   *
   * @return The command request number.
   */
  public long getRequest() {
    return request;
  }

  /**
   * Sets the command request number.
   *
   * @param request The command request number.
   * @return The command entry.
   */
  public TestEntry setRequest(long request) {
    this.request = request;
    return this;
  }

  /**
   * Returns the command response number.
   *
   * @return The command response number.
   */
  public long getResponse() {
    return response;
  }

  /**
   * Sets the command response number.
   *
   * @param response The command response number.
   * @return The command entry.
   */
  public TestEntry setResponse(long response) {
    this.response = response;
    return this;
  }

  @Override
  public void writeObject(BufferOutput buffer, Alleycat alleycat) {
    buffer.writeLong(term).writeLong(request).writeLong(response);
  }

  @Override
  public void readObject(BufferInput buffer, Alleycat alleycat) {
    term = buffer.readLong();
    request = buffer.readLong();
    response = buffer.readLong();
  }

  @Override
  public String toString() {
    return String.format("%s[index=%d, term=%d, request=%d, response=%d]", getClass().getSimpleName(), getIndex(), term, request, response);
  }

}