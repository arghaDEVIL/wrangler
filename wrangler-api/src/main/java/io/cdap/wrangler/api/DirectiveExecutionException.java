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
  * Exception thrown when a directive fails during execution.
  */
 public class DirectiveExecutionException extends Exception {
     private static final long serialVersionUID = 1L;
 
     /**
      * Creates a new exception with the given message.
      *
      * @param message error message
      */
     public DirectiveExecutionException(String message) {
         super(message);
     }
 
     /**
      * Creates a new exception with the given message and cause.
      *
      * @param message error message
      * @param cause root cause
      */
     public DirectiveExecutionException(String message, Throwable cause) {
         super(message, cause);
     }
 }