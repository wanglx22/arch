/*
 *   Licensed to the Apache Software Foundation (ASF) under one
 *   or more contributor license agreements.  See the NOTICE file
 *   distributed with this work for additional information
 *   regarding copyright ownership.  The ASF licenses this file
 *   to you under the Apache License, Version 2.0 (the
 *   "License"); you may not use this file except in compliance
 *   with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing,
 *   software distributed under the License is distributed on an
 *   "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *   KIND, either express or implied.  See the License for the
 *   specific language governing permissions and limitations
 *   under the License.
 *
 */
package org.apache.directory.fortress.core;



import java.util.Properties;


/**
 * This interface prescribes CRUD methods used to manage properties stored within the ldap directory.
 * The Fortress config nodes are used to remotely share Fortress client specific properties between processes.
 * Fortress places no limits on the number of unique configurations that can be present at one time in the directory.
 * The Fortress client will specify the preferred cfg node by name via a property named, 
 * {@link org.apache.directory.fortress.core.GlobalIds#CONFIG_REALM}.
 * Each process using Fortress client is free to share an existing node with other processes or create its own unique config
 * instance using the methods within this class.<BR>
 * <p>
 * This class is thread safe.
 *
 * @author <a href="mailto:dev@directory.apache.org">Apache Directory Project</a>
 */
public interface ConfigMgr
{
    /**
     * Create a new cfg node with given name and properties.  The name is required.  If node already exists,
     * a {@link org.apache.directory.fortress.core.SecurityException} with error 
     * {@link org.apache.directory.fortress.core.GlobalErrIds#FT_CONFIG_ALREADY_EXISTS} will be thrown.
     *
     * @param name    attribute is required and maps to 'cn' attribute in 'device' object class.
     * @param inProps contains {@link Properties} with list of name/value pairs to add to existing config node.
     * @return {@link Properties} containing the collection of name/value pairs just added.
     * @throws org.apache.directory.fortress.core.SecurityException in the event entry already present or other system error.
     */
    Properties add( String name, Properties inProps ) throws SecurityException;


    /**
     * Update existing cfg node with additional properties, or, replace existing properties.  The name is required.  
     * If node does not exist, a {@link org.apache.directory.fortress.core.SecurityException} with error 
     * {@link org.apache.directory.fortress.core.GlobalErrIds#FT_CONFIG_NOT_FOUND} will be thrown.
     *
     * @param name    attribute is required and maps to 'cn' attribute in 'device' object class.
     * @param inProps contains {@link Properties} with list of name/value pairs to add or update from existing config node.
     * @return {@link Properties} containing the collection of name/value pairs to be added to existing node.
     * @throws org.apache.directory.fortress.core.SecurityException in the event entry not present or other system error.
     */
    Properties update( String name, Properties inProps ) throws SecurityException;


    /**
     * This method will update a single property with a new value..
     *
     * @param name of the config node, mostly likely 'DEFAULT'.
     * @param key used for the property.
     * @param value this is old value to be replaced with newValue.
     * @param newValue new value for the property
     * @throws org.apache.directory.fortress.core.SecurityException in the event entry not present or other system error.
     */
    void updateProperty(String name, String key, String value, String newValue) throws SecurityException;


    /**
     * Completely removes named cfg node from the directory.  The name is required.  If node does not exist,
     * a {@link org.apache.directory.fortress.core.SecurityException} with error 
     * {@link org.apache.directory.fortress.core.GlobalErrIds#FT_CONFIG_NOT_FOUND} will be thrown.
     *
     * @param name is required and maps to 'cn' attribute on 'device' object class of node targeted for operation.
     * @throws org.apache.directory.fortress.core.SecurityException in the event of system error.
     */
    void delete( String name ) throws SecurityException;


    /**
     * Delete properties from existing cfg node.  The name is required.  If node does not exist,
     * a {@link org.apache.directory.fortress.core.SecurityException} with error 
     * {@link org.apache.directory.fortress.core.GlobalErrIds#FT_CONFIG_NOT_FOUND} will be thrown.
     *
     * @param name attribute is required and maps to 'cn' attribute in 'device' object class.
     * @param inProps contains the properties to remove.
     * @throws org.apache.directory.fortress.core.SecurityException in the event entry not present or other system error.
     */
    void delete( String name, Properties inProps ) throws SecurityException;


    /**
     * Read an existing cfg node with given name and return to caller.  The name is required.  If node doesn't exist,
     * a {@link org.apache.directory.fortress.core.SecurityException} with error 
     * {@link org.apache.directory.fortress.core.GlobalErrIds#FT_CONFIG_NOT_FOUND} will be thrown.
     *
     * @param name attribute is required and maps to 'cn' attribute in 'device' object class.
     * @return {@link Properties} containing the collection of name/value pairs just added. Maps to 'ftProps' attribute 
     * in 'ftProperties' object class.
     * @throws org.apache.directory.fortress.core.SecurityException in the event entry doesn't exist or other system error.
     */
    Properties read( String name ) throws SecurityException;
}
