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
import net.http.aeon.Aeon;
import net.http.aeon.adapter.TypeAdapter;
import net.http.aeon.elements.ObjectUnit;
import net.http.aeon.handler.layer.*;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Optional;

@Getter
public final class ObjectHandler {

    private final ObjectPattern[] patterns = new ObjectPattern[]{
            new ObjectSeriesLayer(), new ObjectCollectionLayer(), new ObjectEnumerationLayer(), new ObjectPrimitiveLayer(),
            new ObjectMapLayer(), new ObjectRecordLayer(), new ObjectAssortmentLayer(),
    };

    public ObjectUnit write(Class<?> clazz, Object object) {
        if (object == null) {
            return new ObjectUnit.Null();
        }
        Optional<? extends TypeAdapter<?>> adapter = Aeon.getTypeAdapterFactory().getTypeAdapterPool().findIf(clazz);
        if (adapter.isPresent()) {
            return adapter.get().writeInstance(object);
        } else {
            var optional = Aeon.getObjectHandler().findPattern(clazz);
            if (optional.isEmpty()) {
                return new ObjectUnit.Null();
            }
            return optional.get().write(object);
        }
    }


    public Object read(Type type, Class<?> clazz, ObjectUnit unit) {
        if (unit instanceof ObjectUnit.Null) {
            return null;
        }
        Optional<? extends TypeAdapter<?>> adapter = Aeon.getTypeAdapterFactory().getTypeAdapterPool().findIf(clazz);
        if (adapter.isPresent()) {
            try {
                return adapter.get().readInstance(clazz, unit);
            } catch (Exception exception) {
               // return adapter.get(clazz).readCaughtException(exception);
                exception.printStackTrace();
                return null;
            }
        } else {
            Optional<ObjectPattern> optional = Aeon.getObjectHandler().findPattern(clazz);
            if (optional.isEmpty()) {
                return null;
            }
            return optional.get().read(type, clazz, unit);
        }
    }

    public Optional<ObjectPattern> findPattern(Class<?> clazz) {
        return Arrays.stream(this.patterns).filter(it -> it.isElement(clazz)).findFirst();
    }
}