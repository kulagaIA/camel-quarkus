/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.quarkus.component.hazelcast.it;

import io.quarkus.test.common.WithTestResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
@TestHTTPEndpoint(HazelcastAtomicResource.class)
@WithTestResource(HazelcastTestResource.class)
class HazelcastAtomicTest {
    @Test
    public void testAtomicLong() {

        // set value
        given()
                .when()
                .put("/4711")
                .then()
                .statusCode(202);

        // get value
        given()
                .when()
                .get()
                .then()
                .body(equalTo("4711"));

        // increment and get new value
        given()
                .when()
                .get("/increment")
                .then()
                .body(equalTo("4712"));

        // decrement and get new value
        given()
                .when()
                .get("/decrement")
                .then()
                .body(equalTo("4711"));

        // destroy value
        given()
                .when()
                .delete()
                .then()
                .statusCode(202);

    }
}
