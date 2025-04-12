/*
 * Copyright © 2017-2019 Cask Data, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

 package io.cdap.wrangler.api;

 /**
  * Exception thrown when a directive fails to load properly.
  * This typically occurs when there are issues with directive initialization,
  * configuration, or when the directive class cannot be found.
  */
 public class DirectiveLoadException extends Exception {
 
   /**
    * Creates a new exception with the specified error message.
    *
    * @param message the detailed error message
    */
   public DirectiveLoadException(final String message) {
     super(message);
   }
 
   /**
    * Creates a new exception with the specified error message and cause.
    *
    * @param message the detailed error message
    * @param cause the underlying exception that caused this error
    */
   public DirectiveLoadException(final String message, final Exception cause) {
     super(message, cause);
   }
 }