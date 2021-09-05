-- DB
CREATE DATABASE cinema;
CREATE USER cinema WITH PASSWORD 'cinema';
GRANT ALL PRIVILEGES ON DATABASE cinema TO cinema;

-- TABLES

CREATE TABLE public.places
(
    id int NOT NULL,
    row int NOT NULL,
    number int NOT NULL,
    cinema_id int NOT NULL,
    is_deleted boolean NOT NULL,
    CONSTRAINT places_pkey PRIMARY KEY (id)
)
    WITH (OIDS = FALSE) TABLESPACE pg_default;

ALTER TABLE public.places OWNER to cinema;

CREATE SEQUENCE public.places_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE public.places_id_seq OWNED BY public.places.id;
ALTER TABLE ONLY public.places ALTER COLUMN id SET DEFAULT nextval('public.places_id_seq'::regclass);


CREATE TABLE public.cinemas
(
    id int NOT NULL,
    name varchar NOT NULL,
    is_deleted boolean NOT NULL,
    CONSTRAINT cinemas_pkey PRIMARY KEY (id)
)
    WITH (OIDS = FALSE) TABLESPACE pg_default;

ALTER TABLE public.cinemas OWNER to cinema;

CREATE SEQUENCE public.cinemas_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE public.cinemas_id_seq OWNED BY public.cinemas.id;
ALTER TABLE ONLY public.cinemas ALTER COLUMN id SET DEFAULT nextval('public.cinemas_id_seq'::regclass);


CREATE TABLE public.tickets
(
    id int NOT NULL,
    customer varchar(128) NOT NULL,
    place_id int NOT NULL,
    session_id int NOT NULL,
    sale_date timestamp NOT NULL,
    is_deleted boolean NOT NULL,
    CONSTRAINT tickets_pkey PRIMARY KEY (id)
)
    WITH (OIDS = FALSE) TABLESPACE pg_default;

ALTER TABLE public.tickets OWNER to cinema;

CREATE SEQUENCE public.tickets_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE public.tickets_id_seq OWNED BY public.tickets.id;
ALTER TABLE ONLY public.tickets ALTER COLUMN id SET DEFAULT nextval('public.tickets_id_seq'::regclass);


CREATE TABLE public.sessions
(
    id int NOT NULL,
    name varchar(256) NOT NULL,
    cinema_id int NOT NULL,
    is_deleted boolean NOT NULL,
    CONSTRAINT sessions_pkey PRIMARY KEY (id)
)
    WITH (OIDS = FALSE) TABLESPACE pg_default;

ALTER TABLE public.sessions OWNER to cinema;

CREATE SEQUENCE public.sessions_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE public.sessions_id_seq OWNED BY public.sessions.id;
ALTER TABLE ONLY public.sessions ALTER COLUMN id SET DEFAULT nextval('public.sessions_id_seq'::regclass);


-- FOREIGN KEY --

ALTER TABLE public.places ADD CONSTRAINT places_fk FOREIGN KEY (cinema_id) REFERENCES cinemas(id);
ALTER TABLE public.sessions ADD CONSTRAINT sessions_fk FOREIGN KEY (cinema_id) REFERENCES public.cinemas(id);
ALTER TABLE public.tickets ADD CONSTRAINT tickets_fk FOREIGN KEY (place_id) REFERENCES public.places(id);
ALTER TABLE public.tickets ADD CONSTRAINT tickets_fk_1 FOREIGN KEY (session_id) REFERENCES public.sessions(id);
