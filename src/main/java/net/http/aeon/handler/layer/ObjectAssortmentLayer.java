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

package net.http.aeon.handler.layer;

import net.http.aeon.Aeon;
import net.http.aeon.annotations.Comment;
import net.http.aeon.elements.ObjectAssortment;
import net.http.aeon.elements.ObjectUnit;
import net.http.aeon.handler.ObjectPattern;
import net.http.aeon.reflections.AeonReflections;

import java.lang.reflect.Type;
import java.util.Arrays;

public final class ObjectAssortmentLayer implements ObjectPattern {

    public static ObjectAssortmentLayer INSTANCE;

    public ObjectAssortmentLayer() {
        INSTANCE = this;
    }

    @Override
    public boolean isElement(Class<?> clazz) {
        return true;
    }

    @Override
    public ObjectUnit write(Object value) {
        var assortment = new ObjectAssortment();
        Arrays.stream(value.getClass().getDeclaredFields()).forEach(it -> {
            var unit = Aeon.getObjectHandler().write(it.getType(), AeonReflections.get(it, value));
            if (it.isAnnotationPresent(Comment.class)) {
                unit.setComments(it.getDeclaredAnnotation(Comment.class).comment());
            }
            assortment.append(it.getName(), unit);
        });
        return assortment;
    }

    @Override
    public Object read(Type type, Class<?> clazz, ObjectUnit unit) {
        var object = AeonReflections.allocate(clazz);
        if (unit instanceof ObjectAssortment assortment) {
            Arrays.stream(clazz.getDeclaredFields()).forEach(it -> {
                if (assortment.has(it.getName())) {
                    AeonReflections.modify(it, object, Aeon.getObjectHandler().read(it.getGenericType(), it.getType(), assortment.get(it.getName())));
                } else {
                    AeonReflections.modify(it, object, null);
                }
            });
        }
        return object;
    }
}
