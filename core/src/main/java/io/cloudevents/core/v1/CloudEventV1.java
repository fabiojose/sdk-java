/*
 * Copyright 2018-Present The CloudEvents Authors
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package io.cloudevents.core.v1;

import io.cloudevents.SpecVersion;
import io.cloudevents.core.impl.BaseCloudEvent;
import io.cloudevents.rw.CloudEventAttributesWriter;
import io.cloudevents.rw.CloudEventRWException;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

/**
 * CloudEvent implementation for v1.0
 *
 * @author fabiojose
 * @author slinkydeveloper
 */
public final class CloudEventV1 extends BaseCloudEvent {

    private final String id;
    private final URI source;
    private final String type;
    private final String datacontenttype;
    private final URI dataschema;
    private final String subject;
    private final OffsetDateTime time;

    public CloudEventV1(String id, URI source,
                        String type, String datacontenttype,
                        URI dataschema, String subject, OffsetDateTime time,
                        byte[] data, Map<String, Object> extensions) {
        super(data, extensions);

        this.id = id;
        this.source = source;
        this.type = type;
        this.datacontenttype = datacontenttype;
        this.dataschema = dataschema;
        this.subject = subject;
        this.time = time;
    }

    public SpecVersion getSpecVersion() {
        return SpecVersion.V1;
    }

    public String getId() {
        return id;
    }

    public URI getSource() {
        return source;
    }

    public String getType() {
        return type;
    }

    @Override
    public String getDataContentType() {
        return datacontenttype;
    }

    @Override
    public URI getDataSchema() {
        return dataschema;
    }

    public String getSubject() {
        return subject;
    }

    public OffsetDateTime getTime() {
        return time;
    }

    @Override
    public Object getAttribute(String attributeName) {
        switch (attributeName) {
            case "specversion":
                return getSpecVersion();
            case "id":
                return this.id;
            case "source":
                return this.source;
            case "type":
                return this.type;
            case "datacontenttype":
                return this.datacontenttype;
            case "dataschema":
                return this.dataschema;
            case "subject":
                return this.subject;
            case "time":
                return this.time;
        }
        throw new IllegalArgumentException("Spec version v1 doesn't have attribute named " + attributeName);
    }

    @Override
    public void readAttributes(CloudEventAttributesWriter writer) throws CloudEventRWException {
        writer.setAttribute(
            ContextAttributes.ID.name().toLowerCase(),
            this.id
        );
        writer.setAttribute(
            ContextAttributes.SOURCE.name().toLowerCase(),
            this.source
        );
        writer.setAttribute(
            ContextAttributes.TYPE.name().toLowerCase(),
            this.type
        );
        if (this.datacontenttype != null) {
            writer.setAttribute(
                ContextAttributes.DATACONTENTTYPE.name().toLowerCase(),
                this.datacontenttype
            );
        }
        if (this.dataschema != null) {
            writer.setAttribute(
                ContextAttributes.DATASCHEMA.name().toLowerCase(),
                this.dataschema
            );
        }
        if (this.subject != null) {
            writer.setAttribute(
                ContextAttributes.SUBJECT.name().toLowerCase(),
                this.subject
            );
        }
        if (this.time != null) {
            writer.setAttribute(
                ContextAttributes.TIME.name().toLowerCase(),
                this.time
            );
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CloudEventV1 that = (CloudEventV1) o;
        return Objects.equals(getId(), that.getId()) &&
            Objects.equals(getSource(), that.getSource()) &&
            Objects.equals(getType(), that.getType()) &&
            Objects.equals(datacontenttype, that.getDataContentType()) &&
            Objects.equals(dataschema, that.getDataSchema()) &&
            Objects.equals(getSubject(), that.getSubject()) &&
            Objects.equals(getTime(), that.getTime()) &&
            Arrays.equals(getData(), that.getData()) &&
            Objects.equals(this.extensions, that.extensions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSource(), getType(), datacontenttype, dataschema, getSubject(), getTime(), Arrays.hashCode(getData()), this.extensions);
    }

    @Override
    public String toString() {
        return "CloudEvent{" +
            "id='" + id + '\'' +
            ", source=" + source +
            ", type='" + type + '\'' +
            ", datacontenttype='" + datacontenttype + '\'' +
            ", dataschema=" + dataschema +
            ", subject='" + subject + '\'' +
            ", time=" + time +
            ", data=" + Arrays.toString(getData()) +
            ", extensions=" + this.extensions +
            '}';
    }
}
