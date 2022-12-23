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

package net.http.aeon;

import lombok.NonNull;
import net.http.aeon.exceptions.NotImplementedYetException;
import net.http.aeon.handler.ObjectHandler;
import net.http.aeon.io.FileInstanceReader;
import net.http.aeon.io.FileInstanceWriter;
import net.http.aeon.reflections.AeonPathFinder;

public final class Aeon {

    public static final String FILE_EXTENSION = ".ae";
    public static final ObjectHandler instance = new ObjectHandler();

    public static <T> T insert(@NonNull T value) {


        if(AeonPathFinder.isPresent(value)) {
            var unt = new FileInstanceReader(AeonPathFinder.find(value)).read();
            //todo override
            return value;
        }

        var unit = instance.getReader().read(value);
        new FileInstanceWriter(value, AeonPathFinder.find(value), unit);
        return value;
    }

    public static void delete(@NonNull Object value) {
        throw new NotImplementedYetException();
    }

    public static <T> T update(@NonNull T value) {
        throw new NotImplementedYetException();
    }

}
