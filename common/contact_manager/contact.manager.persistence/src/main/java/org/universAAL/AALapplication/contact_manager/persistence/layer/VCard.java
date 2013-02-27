package org.universAAL.AALapplication.contact_manager.persistence.layer;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.universAAL.AALapplication.contact_manager.persistence.layer.Util.*;

/**
 * @author George Fournadjiev
 */
public final class VCard {

  private final String personUri;
  private final String vcardVersion;
  private final Date lastRevision;
  private final String nickname;
  private final String displayName;
  private final String uciLabel;
  private final String uciAdditionalData;
  private final String aboutMe;
  private final Date bday;
  private final String fn;
  private final List<Telephone> telephones;
  private final List<Mail> emails;

  public VCard(String personUri, String vcardVersion, Date lastRevision, String nickname,
               String displayName, String uciLabel, String uciAdditionalData, String aboutMe, Date bday,
               String fn, List<Telephone> telephones, List<Mail> emails) {

    validateParameter(personUri, "personUri");

    this.personUri = personUri;
    this.vcardVersion = vcardVersion;
    this.lastRevision = lastRevision;
    this.nickname = nickname;
    this.displayName = displayName;
    this.uciLabel = uciLabel;
    this.uciAdditionalData = uciAdditionalData;
    this.aboutMe = aboutMe;
    this.bday = bday;
    this.fn = fn;
    this.telephones = telephones;
    this.emails = emails;
  }

  public String getPersonUri() {
    return personUri;
  }

  public String getVcardVersion() {
    return vcardVersion;
  }

  public Date getLastRevision() {
    return lastRevision;
  }

  public String getNickname() {
    return nickname;
  }

  public String getDisplayName() {
    return displayName;
  }

  public String getUciLabel() {
    return uciLabel;
  }

  public String getUciAdditionalData() {
    return uciAdditionalData;
  }

  public String getAboutMe() {
    return aboutMe;
  }

  public Date getBday() {
    return bday;
  }

  public String getFn() {
    return fn;
  }

  public List<Telephone> getTelephones() {
    return Collections.unmodifiableList(telephones);
  }

  public List<Mail> getEmails() {
    return Collections.unmodifiableList(emails);
  }

  @Override
  public String toString() {
    return "VCard{" +
        "personUri='" + personUri + '\'' +
        ", vcardVersion='" + vcardVersion + '\'' +
        ", lastRevision=" + lastRevision +
        ", nickname='" + nickname + '\'' +
        ", displayName='" + displayName + '\'' +
        ", uciLabel='" + uciLabel + '\'' +
        ", uciAdditionalData='" + uciAdditionalData + '\'' +
        ", aboutMe='" + aboutMe + '\'' +
        ", bday=" + bday +
        ", fn='" + fn + '\'' +
        ", telephones=" + telephones +
        ", emails=" + emails +
        '}';
  }
}
