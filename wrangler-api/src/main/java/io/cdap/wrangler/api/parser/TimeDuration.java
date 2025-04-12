/*
 *  Copyright © 2017-2019 Cask Data, Inc.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not
 *  use this file except in compliance with the License. You may obtain a copy of
 *  the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 *  WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 *  License for the specific language governing permissions and limitations under
 *  the License.
 */
package io.cdap.wrangler.api.parser;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

public class TimeDuration implements Token {
  private final String raw;
  private final long nanoseconds;

  public TimeDuration(String raw) {
    this.raw = raw;
    this.nanoseconds = parseDuration(raw);
  }

  private long parseDuration(String value) {
    value = value.trim().toLowerCase();

    if (value.endsWith("ns")) {
      return (long) Double.parseDouble(value.replace("ns", ""));
    } else if (value.endsWith("ms")) {
      return (long) (Double.parseDouble(value.replace("ms", "")) * 1_000_000);
    } else if (value.endsWith("s")) {
      return (long) (Double.parseDouble(value.replace("s", "")) * 1_000_000_000);
    } else if (value.endsWith("m")) {
      return (long) (Double.parseDouble(value.replace("m", "")) * 60_000_000_000L);
    } else if (value.endsWith("h")) {
      return (long) (Double.parseDouble(value.replace("h", "")) * 3600_000_000_000L);
    }

    throw new IllegalArgumentException("Unsupported time unit in value: " + value);
  }

  public long getNanoseconds() {
    return nanoseconds;
  }

  public double getMilliseconds() {
    return nanoseconds / 1_000_000.0;
  }

  public double getSeconds() {
    return nanoseconds / 1_000_000_000.0;
  }

  @Override
  public Object value() {
    return nanoseconds;
  }

  @Override
  public TokenType type() {
    return TokenType.TIME_DURATION;
  }

  @Override
  public JsonElement toJson() {
    return new JsonPrimitive(raw);
  }
}



