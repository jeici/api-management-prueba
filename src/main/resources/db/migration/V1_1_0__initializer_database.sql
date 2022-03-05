CREATE SCHEMA IF NOT EXISTS logistic_prueba;

CREATE TABLE IF NOT EXISTS "logistic_prueba"."users" (
                                                         "id" SERIAL PRIMARY KEY,
                                                         "username" varchar,
                                                         "email" varchar,
                                                         "password" varchar,
                                                         "created_at" timestamp DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS "logistic_prueba"."rols" (
                                                        "id" SERIAL PRIMARY KEY,
                                                        "name" varchar
);

CREATE TABLE IF NOT EXISTS "logistic_prueba"."user_rols" (
                                                             "user_id" int,
                                                             "rol_id" int
);

CREATE TABLE IF NOT EXISTS "logistic_prueba"."products" (
                                                            "id" SERIAL PRIMARY KEY,
                                                            "name" varchar,
                                                            "description" varchar,
                                                            "price" varchar,
                                                            "status" boolean,
                                                            "created_at" timestamp DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS "logistic_prueba"."warehouses" (
                                                              "id" SERIAL PRIMARY KEY,
                                                              "name" varchar,
                                                              "description" varchar,
                                                              "type" int,
                                                              "status" boolean,
                                                              "created_at" timestamp DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS "logistic_prueba"."logistics" (
                                                             "id" SERIAL PRIMARY KEY,
                                                             "name" varchar,
                                                             "description" varchar,
                                                             "discount_rate" float8,
                                                             "status" boolean,
                                                             "created_at" timestamp DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS "logistic_prueba"."customers" (
                                                             "id" SERIAL PRIMARY KEY,
                                                             "full_name" varchar,
                                                             "phone" varchar,
                                                             "email" varchar,
                                                             "address" varchar,
                                                             "status" boolean,
                                                             "created_at" timestamp DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS "logistic_prueba"."deliveries" (
                                                              "id" SERIAL PRIMARY KEY,
                                                              "customer_id" int,
                                                              "logistic_id" int,
                                                              "warehouse_id" int,
                                                              "product_id" int,
                                                              "price" float8,
                                                              "delivery_date" timestamp,
                                                              "discount" float8,
                                                              "quantity" int,
                                                              "total" float8,
                                                              "transport_number" varchar,
                                                              "traking_number" varchar,
                                                              "status" boolean,
                                                              "created_at" timestamp DEFAULT NOW()
);

ALTER TABLE "logistic_prueba"."user_rols" ADD FOREIGN KEY ("user_id") REFERENCES "logistic_prueba"."users" ("id");

ALTER TABLE "logistic_prueba"."user_rols" ADD FOREIGN KEY ("rol_id") REFERENCES "logistic_prueba"."rols" ("id");

ALTER TABLE "logistic_prueba"."deliveries" ADD FOREIGN KEY ("customer_id") REFERENCES "logistic_prueba"."customers" ("id");

ALTER TABLE "logistic_prueba"."deliveries" ADD FOREIGN KEY ("product_id") REFERENCES "logistic_prueba"."products" ("id");

ALTER TABLE "logistic_prueba"."deliveries" ADD FOREIGN KEY ("logistic_id") REFERENCES "logistic_prueba"."logistics" ("id");

ALTER TABLE "logistic_prueba"."deliveries" ADD FOREIGN KEY ("warehouse_id") REFERENCES "logistic_prueba"."warehouses" ("id");
