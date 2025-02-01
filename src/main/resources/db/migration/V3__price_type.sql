ALTER TABLE goods
ALTER COLUMN price TYPE double precision
USING price::double precision;