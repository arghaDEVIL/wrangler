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

 import io.cdap.wrangler.api.parser.SyntaxError;
 import java.util.Iterator;
 
 /**
  * Exception thrown when there is an error parsing a directive specification.
  * This exception may contain one or more syntax errors encountered during parsing.
  */
 public class DirectiveParseException extends Exception {
     private static final long serialVersionUID = 1L;
     private final Iterator<SyntaxError> syntaxErrors;
 
     /**
      * Constructs a new DirectiveParseException with the specified message.
      *
      * @param message the error message describing the parse failure
      */
     public DirectiveParseException(final String message) {
         super(message);
         this.syntaxErrors = null;
     }
 
     /**
      * Constructs a new DirectiveParseException with the specified message and errors.
      *
      * @param message the error message describing the parse failure
      * @param errors iterator containing syntax errors encountered during parsing
      */
     public DirectiveParseException(final String message, final Iterator<SyntaxError> errors) {
         super(message);
         this.syntaxErrors = errors;
     }
 
     /**
      * Constructs a new DirectiveParseException with the specified message and cause.
      *
      * @param message the error message describing the parse failure
      * @param cause the underlying cause of the parse exception
      */
     public DirectiveParseException(final String message, final Throwable cause) {
         super(message, cause);
         this.syntaxErrors = null;
     }
 
     /**
      * Constructs a new DirectiveParseException with the specified message, errors, and cause.
      *
      * @param message the error message describing the parse failure
      * @param errors iterator containing syntax errors encountered during parsing
      * @param cause the underlying cause of the parse exception
      */
     public DirectiveParseException(final String message, final Iterator<SyntaxError> errors, 
             final Throwable cause) {
         super(message, cause);
         this.syntaxErrors = errors;
     }
 
     /**
      * Constructs a new DirectiveParseException with the specified cause.
      *
      * @param cause the underlying cause of the parse exception
      */
     public DirectiveParseException(final Throwable cause) {
         super(cause);
         this.syntaxErrors = null;
     }
 
     /**
      * Constructs a new DirectiveParseException with a formatted message.
      *
      * @param directiveName the name of the directive where parsing failed
      * @param errorMessage the specific error message
      */
     public DirectiveParseException(final String directiveName, final String errorMessage) {
         this(String.format("Error encountered while parsing '%s': %s", directiveName, errorMessage));
     }
 
     /**
      * Constructs a new DirectiveParseException with a formatted message and cause.
      *
      * @param directiveName the name of the directive where parsing failed
      * @param errorMessage the specific error message
      * @param cause the underlying cause of the parse exception
      */
     public DirectiveParseException(final String directiveName, final String errorMessage,
             final Throwable cause) {
         this(String.format("Error encountered while parsing '%s': %s", directiveName, errorMessage),
                 cause);
     }
 
     /**
      * Returns the syntax errors that caused this exception.
      *
      * @return iterator of syntax errors, or null if no syntax errors were recorded
      */
     public Iterator<SyntaxError> errors() {
         return syntaxErrors;
     }
 }