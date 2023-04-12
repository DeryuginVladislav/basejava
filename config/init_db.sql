CREATE TABLE resume
(
    uuid      CHAR(36) PRIMARY KEY NOT NULL,
    full_name TEXT                 NOT NULL
);

CREATE TABLE contact
(
    id          SERIAL PRIMARY KEY,
    resume_uuid CHAR(36) NOT NULL REFERENCES resume (uuid) ON DELETE CASCADE,
    type        TEXT     NOT NULL,
    value       TEXT     NOT NULL
);
CREATE UNIQUE INDEX contact_uuid_type_index
    ON contact (resume_uuid, type);

create table section
(
    id          integer primary key not null default nextval('section_id_seq'::regclass),
    resume_uuid character(36)       not null,
    type        text                not null,
    value       text                not null,
    foreign key (resume_uuid) references public.resume (uuid)
        match simple on update no action on delete cascade
);
create unique index section_uuid_type_index on section using btree (resume_uuid, type);

