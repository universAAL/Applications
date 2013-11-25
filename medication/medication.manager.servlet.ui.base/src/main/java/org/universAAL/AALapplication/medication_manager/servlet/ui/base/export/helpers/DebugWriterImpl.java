/*******************************************************************************
 * Copyright 2012 , http://www.prosyst.com - ProSyst Software GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package org.universAAL.AALapplication.medication_manager.servlet.ui.base.export.helpers;

import org.universAAL.AALapplication.medication_manager.servlet.ui.base.impl.MedicationManagerServletUIBaseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import static org.universAAL.AALapplication.medication_manager.servlet.ui.base.impl.Activator.*;

/**
 * @author George Fournadjiev
 */
public final class DebugWriterImpl implements DebugWriter {

  private final PrintStream ps;

  public DebugWriterImpl() {
    try {
      File dir = getMedicationManagerConfigurationDirectory();
      File file = new File(dir, "debug.log");
      FileOutputStream out = new FileOutputStream(file);
      this.ps = new PrintStream(out);
    } catch (FileNotFoundException e) {
      throw new MedicationManagerServletUIBaseException(e);
    }

  }

  public void println(String line) {
     ps.println(line);
  }
}
