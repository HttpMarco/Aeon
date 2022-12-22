/*
 * Copyright 2022 Aeon contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.http.aeon.handler;

import lombok.Getter;
import net.http.aeon.handler.layer.ObjectAssortmentLayer;
import net.http.aeon.handler.layer.ObjectPrimitiveLayer;
import net.http.aeon.handler.reader.ObjectPatternReader;
import java.util.Arrays;
import java.util.Optional;

public final class ObjectHandler {

    private final ObjectPattern[] patterns = new ObjectPattern[]{new ObjectPrimitiveLayer(), new ObjectAssortmentLayer()};

    @Getter
    private final ObjectPatternReader reader;

    public ObjectHandler() {
        this.reader = new ObjectPatternReader();
    }

    public Optional<ObjectPattern> findPattern(Class<?> clazz) {
        return Arrays.stream(this.patterns).filter(it -> it.isElement(clazz)).findFirst();
    }

}