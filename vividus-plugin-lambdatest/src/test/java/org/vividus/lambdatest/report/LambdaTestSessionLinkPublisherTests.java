/*
 * Copyright 2019-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.vividus.lambdatest.report;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;

class LambdaTestSessionLinkPublisherTests
{
    @Test
    void shouldReturnSessionUrl()
    {
        LambdaTestSessionLinkPublisher publisher = new LambdaTestSessionLinkPublisher(null, null, null);
        assertEquals(Optional.of("https://automation.lambdatest.com/logs/?sessionID=session-id"),
                publisher.getSessionUrl("session-id"));
    }
}
