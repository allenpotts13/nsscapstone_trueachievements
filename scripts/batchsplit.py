import json
import os

def split_json(input_file, batch_size):
    with open(input_file, 'r') as file:
        data = json.load(file)

    games_list = data.get("games", [])

    # Split the list into batches
    batches = [games_list[i:i + batch_size] for i in range(0, len(games_list), batch_size)]

    # Create a directory for the batches
    output_dir = os.path.splitext(input_file)[0] + "_batches"
    os.makedirs(output_dir, exist_ok=True)

    # Save each batch as a separate JSON file
    for i, batch in enumerate(batches, start=1):
        output_file = os.path.join(output_dir, f"batch_{i}.json")
        with open(output_file, 'w', encoding='utf-8') as batch_file:
            json.dump({"games": batch}, batch_file, indent=2)

        print(f"Batch {i} saved to {output_file}")

if __name__ == "__main__":
    # Specify your input file path and desired batch size
    input_file_path = r'C:\Users\curse\OneDrive\Documents\NSS\csvtojson.json'
    batch_size = 25

    split_json(input_file_path, batch_size)
